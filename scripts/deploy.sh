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

echo "$SQL_INSTANCE_CONNECTION_NAME, $SQL_INSTANCE_NAME"

VM_NAME="instance-deployed-integration"
ZONE="europe-west1-b"
MACHINE_TYPE="e2-small"
IMAGE_FAMILY="ubuntu-2204-lts"
IMAGE_PROJECT="ubuntu-os-cloud"
TARGET_TAGS="http-server,ssl-rule-tag,ssh,https-server,default-allow-ssh"
DUCK_TOKEN=2836d713-b14a-404a-83ee-6d67c4f93d86
DUCK_DNS=youthcouncil
EMAIL=seifeldin.sabry@student.kdg.be

function set_project() {
  echo "Setting project to ${GOOGLE_PROJECT_ID}"
  gcloud config set project "${GOOGLE_PROJECT_ID}"
}

function create_vm() {
#  echo "Creating VM ${VM_NAME} in zone ${ZONE} with machine type ${MACHINE_TYPE} and image family ${IMAGE_FAMILY}"
#  if gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID"; then
#    echo "VM ${VM_NAME} already exists"
#    return 0
#  fi
#  gcloud compute instances delete "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --quiet
  gcloud compute instances create "$VM_NAME" \
      --zone=$ZONE \
      --machine-type=$MACHINE_TYPE \
      --tags=$TARGET_TAGS \
      --image-family=$IMAGE_FAMILY \
      --image-project=$IMAGE_PROJECT \
      --project="${GOOGLE_PROJECT_ID}" \
      --metadata=startup-script="#! /bin/bash
      apt-get update && apt install -y openjdk-17-jdk
      curl -L -o /tmp/gradle-7.4.2-bin.zip https://services.gradle.org/distributions/gradle-7.4.2-bin.zip
      apt-get update && apt-get -y install unzip
      unzip -d /opt/gradle /tmp/gradle-7.4.2-bin.zip
      apt-get clean
      ufw allow 80/tcp
      ufw allow 443/tcp
      ufw allow 22/tcp
      ufw enable
      snap install core
      snap refresh core
      snap install --classic certbot
      ln -s /snap/bin/certbot /usr/bin/certbot
      sleep 5
      certbot certonly -n -d $DUCK_DNS.duckdns.org --agree-tos --email $EMAIL --webroot
      curl -k \"https://www.duckdns.org/update?domains=$DUCK_DNS&token=$DUCK_TOKEN&ip=\""
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
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "IFS='
  '
  for process in \$(ps aux | grep java | grep -v grep); do
    echo 'killing process' \$process'
    kill -9 \$(echo \$process | awk '{print \$2}')
  done
  unset IFS"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "gcloud auth activate-service-account --key-file secret.json"
  echo "current permissions"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "gcloud auth list; gcloud config list"
  echo "attempting to run jar"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "for VAR in ${ENV_VARIABLES[*]}; do
  key=\"\${VAR%=*}\"
  value=\"\${VAR#*=}\"
  export \"\$key\"=\"\$value\" 2> /dev/null
done
  export HOME_DIR=\$(pwd) && export PATH_TO_SECRET=\$HOME_DIR/secret.json && java -jar build.jar &"
}

function setup_database {
#  create Database if it does not exist
  gcloud sql databases describe "$POSTGRES_DB" --instance=$SQL_INSTANCE_NAME -q || gcloud sql databases create "$POSTGRES_DB" --instance="$SQL_INSTANCE_NAME"
  for file in ~/sql-data/*.sql; do
    gcloud sql import sql "$SQL_INSTANCE_NAME" "$file" --database="$POSTGRES_DB"
  done
}

function establish_connection_to_vm() {
    echo "Waiting for VM to be ready..."
    while ! gcloud compute ssh $VM_NAME --zone=$ZONE --command="java --version" 2> /dev/null; do
      sleep 1
    done
}

function get_instance_ip() {
  echo "Getting VM IP"
  VM_IP=$(gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --format="get(networkInterfaces[0].accessConfigs[0].natIP)")
  echo "VM IP is $VM_IP"
}

function authorize_vm_to_instance() {
#  if gcloud instance does not exist then exit
  if ! gcloud sql instances describe "$SQL_INSTANCE_NAME" -q; then
    echo "Instance $SQL_INSTANCE_NAME does not exist"
    exit 1
  fi
  echo "Authorizing VM to connect to postgres instance"
#  TODO: change this to the VM's IP
  gcloud sql instances patch "$SQL_INSTANCE_NAME" --authorized-networks="34.140.220.86" --quiet
}

function authenticate() {
  echo "Authenticating to gcloud"
  gcloud auth activate-service-account --key-file "$GOOGLE_SERVICE_ACCOUNT_FILE"
  gcloud auth list
  gcloud config list
}

authenticate
set_project
create_vm
get_instance_ip
#authorize_vm_to_instance
establish_connection_to_vm
copy_files_over
