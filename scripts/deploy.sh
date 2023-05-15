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
SQL_INSTANCE_NAME="youthcouncil"
REGION="europe-west1"
SQL_ROOT_PASSWORD="root"
ZONE="europe-west1-b"
MACHINE_TYPE="e2-small"
IMAGE_FAMILY="ubuntu-2204-lts"
IMAGE_PROJECT="ubuntu-os-cloud"
TARGET_TAGS="http-server,ssl-rule-tag,ssh,https-server,default-allow-ssh"
DUCK_TOKEN=2836d713-b14a-404a-83ee-6d67c4f93d86
DUCK_DNS=youthcouncil
EMAIL=seifeldin.sabry@student.kdg.be
SYSTEMD_SERVICE_NAME="youthcouncil.service"
SYSTEMD_SERVICE_PATH="/etc/systemd/system/${SYSTEMD_SERVICE_NAME}"
SYSTEMD_SERVICE_CONTENT="[Unit]
Description=Youthcouncil service
After=network.target

[Service]
Type=simple
User=root
WorkingDirectory=/web
ExecStart=bash /web/var.sh"

additional_systemd="
Restart=on-failure

[Install]
WantedBy=multi-user.target
"

varsh_content="#!/bin/bash
for line in \$(cat /web/.env); do
  export \$line
done
echo \"$additional_systemd\" >> $SYSTEMD_SERVICE_PATH
java -jar /web/build.jar
"

function set_project() {
  echo "Setting project to ${GOOGLE_PROJECT_ID}"
  gcloud config set project "${GOOGLE_PROJECT_ID}"
}

function create_vm() {
  if gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --quiet 1>/dev/null 2>/dev/null; then
    echo "VM ${VM_NAME} already exists"
    echo "Deleting VM ${VM_NAME}"
    gcloud compute instances delete "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --quiet
  fi
  gcloud compute instances create "$VM_NAME" \
      --zone="$ZONE" \
      --machine-type="$MACHINE_TYPE" \
      --tags="$TARGET_TAGS" \
      --image-family="$IMAGE_FAMILY" \
      --image-project="$IMAGE_PROJECT" \
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
      mkdir /web
      echo \"${SYSTEMD_SERVICE_CONTENT}\" > ${SYSTEMD_SERVICE_PATH}
      echo \"${varsh_content}\" > /web/var.sh
      chmod +x /web/var.sh
      for VAR in ${ENV_VARIABLES[*]}; do
        echo \$VAR >> /web/env
      done
      systemctl daemon-reload
      snap install core
      snap refresh core
      snap install --classic certbot
      ln -s /snap/bin/certbot /usr/bin/certbot
      sleep 5
      curl -k \"https://www.duckdns.org/update?domains=$DUCK_DNS&token=$DUCK_TOKEN&ip=\""
}

function authorize_vm_to_instance() {
#  use gcloud instances list to find the instance, if not exist exit 1
 if ! gcloud sql instances list --project="$GOOGLE_PROJECT_ID" | grep "$SQL_INSTANCE_NAME" 1>/dev/null 2>/dev/null; then
    echo "Instance $SQL_INSTANCE_NAME does not exist"
    create_sql_instance
  fi
# Get the list of current authnw
  current_authnw=$(gcloud sql instances describe "$SQL_INSTANCE_NAME" \
    --format='value(settings.ipConfiguration.authorizedNetworks[].value)' \
    |tr ";" ",")
  if [[ -z ${current_authnw} || ${current_authnw} = "0.0.0.0/0" ]]; then
    gcloud sql instances patch "$SQL_INSTANCE_NAME" --authorized-networks="${VM_IP}" -q
  else
    current_authnw+=,${VM_IP}
    gcloud sql instances patch "$SQL_INSTANCE_NAME" \
        --authorized-networks="${current_authnw}" -q
  fi
}

function get_instance_ip() {
  echo "Getting VM IP"
  VM_IP=$(gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --format="get(networkInterfaces[0].accessConfigs[0].natIP)" 2>/dev/null)
  echo "VM IP is $VM_IP"
}

function copy_files_over() {
  echo "Copying authentication files over to VM"
  cat "$GOOGLE_SERVICE_ACCOUNT_FILE" > ./secret.json
  echo "Copying file over to VM"
  gcloud compute scp --recurse ./secret.json --zone=$ZONE "$VM_NAME":/web/secret.json
  echo "removing jar on VM"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "rm -rf FinalProject-0.0.1-SNAPSHOT.jar 2> /dev/null"
  echo "Copying jar over to VM"
  gcloud compute scp --zone=$ZONE ./build/libs/FinalProject-0.0.1-SNAPSHOT.jar "$VM_NAME":/web/build.jar
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "killall java"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "gcloud auth activate-service-account --key-file secret.json"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "certbot certonly --standalone -n -d $DUCK_DNS.duckdns.org --agree-tos --email $EMAIL"
  echo "attempting to run jar"
  gcloud compute ssh --zone=$ZONE "$VM_NAME" --command "for VAR in ${ENV_VARIABLES[*]}; do
  key=\"\${VAR%=*}\"
  value=\"\${VAR#*=}\"
  export \"\$key\"=\"\$value\" 2> /dev/null
done
  export HOME_DIR=\$(pwd) && export PATH_TO_SECRET=\$HOME_DIR/secret.json && systemctl start \"${SYSTEMD_SERVICE_NAME}\""
  echo "Jar is running"
}


function establish_connection_to_vm() {
    echo "Waiting for VM to be ready..."
    while ! gcloud compute ssh $VM_NAME --zone=$ZONE --command="java --version && which certbot" 2> /dev/null; do
      sleep 1
    done
}

function create_sql_instance() {
  echo "Creating SQL instance"
  gcloud sql instances create "$SQL_INSTANCE_NAME" \
    --database-version=POSTGRES_14 \
    --cpu=1 \
    --memory=3840MiB \
    --region="$REGION" \
    --project="$GOOGLE_PROJECT_ID" \
    --root-password="$SQL_ROOT_PASSWORD" \
    --storage-type=HDD \
    --tier=db-f1-micro
}

function authenticate() {
  echo "Authenticating to gcloud"
  gcloud auth activate-service-account --key-file "$GOOGLE_SERVICE_ACCOUNT_FILE"
  gcloud auth list
  gcloud config list
}


function setup_database() {
  echo "Setting up database $POSTGRES_DB"
  if ! gcloud sql databases list --instance="$SQL_INSTANCE_NAME" --project="$GOOGLE_PROJECT_ID" | grep "$POSTGRES_DB" 1>/dev/null 2>/dev/null; then
    echo "Database $POSTGRES_DB does not exist"
    gcloud sql databases create "$POSTGRES_DB" --instance="$SQL_INSTANCE_NAME" --project="$GOOGLE_PROJECT_ID"
  fi
  echo "Database $POSTGRES_DB already exists"
}

authenticate
set_project
create_vm
get_instance_ip
authorize_vm_to_instance
setup_database
establish_connection_to_vm
copy_files_over
