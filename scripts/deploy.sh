#!/bin/bash

# Author: Seifeldin Sabry
# Function: deploy a VM on gcloud with apache2 installed and configured with a self-signed certificate


VM_NAME="instance-deployed-integration"
ZONE="europe-west1-b"
MACHINE_TYPE="e2-small"
IMAGE_FAMILY="ubuntu-2204-lts"
IMAGE_PROJECT="ubuntu-os-cloud"
TARGET_TAGS="http-server,ssl-rule-tag,ssh,https-server,default-allow-ssh"
DUCK_TOKEN=2836d713-b14a-404a-83ee-6d67c4f93d86
DUCK_DNS=youthcouncil
EMAIL=seifeldin.sabry@student.kdg.be
POSTGRES_INSTANCE_NAME="youthcouncil"
POSTGRES_INSTANCE_IP=$(gcloud sql instances describe $POSTGRES_INSTANCE_NAME --format="value(ipAddresses.ipAddress)")

POSTGRES_DB_NAME="YouthCouncil"

ENV_VARIABLES=("$@")
function create_vm() {
  gcloud compute instances describe $VM_NAME &> /dev/null && return
  gcloud compute instances create "$VM_NAME" \
      --zone=$ZONE \
      --machine-type=$MACHINE_TYPE \
      --tags=$TARGET_TAGS \
      --image-family=$IMAGE_FAMILY \
      --image-project=$IMAGE_PROJECT \
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
      certbot -n -d $DUCK_DNS.duckdns.org --agree-tos --email $EMAIL
      curl -k \"https://www.duckdns.org/update?domains=$DUCK_DNS&token=$DUCK_TOKEN&ip=\"
      export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
      export PATH=\$PATH:\$JAVA_HOME/bin
      exec /bin/bash
      for VAR in ${ENV_VARIABLES[*]}; do
        export \$VAR
      done
      unset IFS"
}

function copy_files_over() {
    gcloud compute scp --recurse ../build "$VM_NAME":~/

  #  gcloud compute ssh "$VM_NAME" --command "
  #  curl "http://169.254.169.254/computeMetadata/v1/instance/attributes/env_variables" -H "Metadata-Flavor: Google" > .env
  #  cat .env
  #  IFS=$'\n'
  #  for VAR in \$(cat .env); do
  #    delimiter='='
  #    var_name=\${VAR%%\$delimiter*}
  #    var_value=\${VAR#*\$delimiter}
  #    export \$var_name=\$var_value
  #  done
  #  "
    gcloud compute ssh "$VM_NAME" --command "echo \$POSTGRES_PASSWORD"
    gcloud compute ssh "$VM_NAME" --command "echo \$POSTGRES_USERNAME"
    gcloud compute ssh "$VM_NAME" --command "java -jar build/libs/FinalProject-0.0.1-SNAPSHOT.jar"
}

function setup_database {
  gcloud sql connect $POSTGRES_INSTANCE_NAME --user=postgres << EOF
  CREATE DATABASE IF NOT EXISTS $POSTGRES_DB_NAME;
  CREATE USER youthcouncil WITH ENCRYPTED PASSWORD 'youthcouncil';
  GRANT ALL PRIVILEGES ON DATABASE youthcouncil TO youthcouncil;
EOF
}

function establish_connection_to_vm() {
  while true; do
    echo "Waiting for VM to be ready..."
    sleep 5
    gcloud compute ssh $VM_NAME --zone=$ZONE --command="echo 'VM is ready'" 2>/dev/null
    if [ $? -eq 0 ]; then
      break
    fi
  done
}

create_vm
establish_connection_to_vm
copy_files_over
