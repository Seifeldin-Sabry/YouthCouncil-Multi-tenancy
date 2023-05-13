#!/bin/bash

function create_vm() {
  echo "Creating VM ${VM_NAME} in zone ${ZONE} with machine type ${MACHINE_TYPE} and image family ${IMAGE_FAMILY}"
  if gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --quiet 2>/dev/null; then
    echo "VM ${VM_NAME} already exists"
    return 0
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
      snap install core
      snap refresh core
      snap install --classic certbot
      ln -s /snap/bin/certbot /usr/bin/certbot
      sleep 5
      certbot certonly -n -d $DUCK_DNS.duckdns.org --agree-tos --email $EMAIL --webroot
      curl -k \"https://www.duckdns.org/update?domains=$DUCK_DNS&token=$DUCK_TOKEN&ip=\""
}

function setup_database {
#  create Database if it does not exist
  if ! gcloud sql databases describe "$DATABASE_NAME" --instance="$SQL_INSTANCE_NAME" --quiet 2>/dev//null; then
    echo "Creating database $DATABASE_NAME"
    gcloud sql databases create "$DATABASE_NAME" --instance="$SQL_INSTANCE_NAME" --quiet
  fi
}

function authorize_vm_to_instance() {
#  if gcloud instance does not exist then exit
  echo "Authorizing VM to connect to postgres instance $SQL_INSTANCE_NAME"
#  use gcloud instances list to find the instance, if not exist exit 1
 if ! gcloud sql instances describe "$SQL_INSTANCE_NAME" --project="$GOOGLE_PROJECT_ID" 2> /dev/null; then
    echo "Creating SQL instance $SQL_INSTANCE_NAME"
    gcloud sql instances create "$SQL_INSTANCE_NAME" --database-version=POSTGRES_14 --cpu=1 --memory=3840MiB --region=europe-west1 --project="$GOOGLE_PROJECT_ID"
  fi
  echo "Authorizing VM to connect to postgres instance $SQL_INSTANCE_NAME the IP $VM_IP"
  gcloud sql instances patch "$SQL_INSTANCE_NAME" --authorized-networks="$VM_IP" --quiet
}

function get_instance_ip() {
  echo "Getting VM IP"
  VM_IP=$(gcloud compute instances describe "$VM_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" --format="get(networkInterfaces[0].accessConfigs[0].natIP)" 2>/dev/null)
  echo "VM IP is $VM_IP"
}

VM_NAME="instance-deployed-integration"
ZONE="europe-west1-b"
MACHINE_TYPE="e2-small"
IMAGE_FAMILY="ubuntu-2204-lts"
IMAGE_PROJECT="ubuntu-os-cloud"
TARGET_TAGS="http-server,ssl-rule-tag,ssh,https-server,default-allow-ssh"
DUCK_TOKEN=2836d713-b14a-404a-83ee-6d67c4f93d86
DUCK_DNS=youthcouncil
EMAIL=seifeldin.sabry@student.kdg.be
DATABASE_NAME=YouthCouncil
SQL_INSTANCE_NAME=youthcouncil
GOOGLE_PROJECT_ID=infra3-seifeldin-sabry

create_vm
get_instance_ip
authorize_vm_to_instance
setup_database
