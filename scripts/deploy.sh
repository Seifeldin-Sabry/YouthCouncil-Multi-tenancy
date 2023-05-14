#!/bin/bash

# Author: Seifeldin Sabry
# Function: deploy a VM on gcloud with apache2 installed and configured with a self-signed certificate

ENV_VARIABLES=("$@")

if [[ ${#ENV_VARIABLES[@]} -eq 0 ]]; then
  echo "No environment variables provided"
  exit 1
fi

for VAR in ${ENV_VARIABLES[*]}; do
  key="${VAR%=*}"
  value="${VAR#*=}"
  export "$key"="$value" 2> /dev/null
done

VM_NAME="instance-deployed-integration"
ZONE="europe-west1-b"

function set_project() {
  echo "Setting project to ${GOOGLE_PROJECT_ID}"
  gcloud config set project "${GOOGLE_PROJECT_ID}"
}

function copy_files_over() {
  echo "Copying authentication files over to VM"
  cat "$GOOGLE_SERVICE_ACCOUNT_FILE" > ./secret.json
  ls -a
  echo "Copying file over to VM"
  gcloud compute scp --recurse ./secret.json --zone=$ZONE "$VM_NAME":~/secret.json
  echo "removing jar on VM"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "rm -rf FinalProject-0.0.1-SNAPSHOT.jar 2> /dev/null"
  echo "Copying jar over to VM"
  gcloud compute scp --zone=$ZONE ./build/libs/FinalProject-0.0.1-SNAPSHOT.jar "$VM_NAME":~/build.jar
  echo "attempting authentication"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "killall java"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "gcloud auth activate-service-account --key-file secret.json"
  echo "current permissions"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "gcloud auth list; gcloud config list"
  echo "attempting to run jar"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "for VAR in ${ENV_VARIABLES[*]}; do
  key=\"\${VAR%=*}\"
  value=\"\${VAR#*=}\"
  export \"\$key\"=\"\$value\" 2> /dev/null
done
  export HOME_DIR=\$(pwd) && export PATH_TO_SECRET=\$HOME_DIR/secret.json && java -jar build.jar & disown"
}


function establish_connection_to_vm() {
    echo "Waiting for VM to be ready..."
    while ! gcloud compute ssh $VM_NAME --zone=$ZONE --command="java --version" 2> /dev/null; do
      sleep 1
    done
}

function authenticate() {
  echo "Authenticating to gcloud"
  gcloud auth activate-service-account --key-file "$GOOGLE_SERVICE_ACCOUNT_FILE"
  gcloud auth list
  gcloud config list
}

function check_VM() {
  echo "Checking if VM exists"
  if gcloud compute instances list --zones=$ZONE --project="$GOOGLE_PROJECT_ID" | grep ${VM_NAME}; then
    echo "VM exists"
  else
    echo "VM does not exist"
    echo "Run the initialise_cloud.sh script to setup the deployment environment"
    exit 1
  fi
}

check_VM
authenticate
set_project
establish_connection_to_vm
copy_files_over
