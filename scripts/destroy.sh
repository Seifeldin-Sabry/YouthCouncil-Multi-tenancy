#!/bin/bash

ZONE="europe-west1-b"
SQL_INSTANCE_NAME=$1
VM_PATTERN=$2
GOOGLE_PROJECT_ID=$3
GOOGLE_SERVICE_ACCOUNT_FILE=$4

function delete_sql_instance() {
  echo "Deleting SQL instance: $SQL_INSTANCE_NAME"
  gcloud sql instance describe "$SQL_INSTANCE_NAME" -q || echo "SQL instance $SQL_INSTANCE_NAME does not exist" && return 0
  gcloud sql instance delete "$SQL_INSTANCE_NAME" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" -q
}

function delete_deployed_VMs() {
  VMS_TO_DELETE=$(gcloud compute instances list --filter="name~$VM_PATTERN" --format="value(name)")
  if [[ -n "$VMS_TO_DELETE" ]]; then
    for vm in $VMS_TO_DELETE; do
      echo "Deleting VM: $vm"
      gcloud compute instances delete "$vm" --zone="$ZONE" --project="$GOOGLE_PROJECT_ID" -q
    done
  fi
}

function authenticate() {
  echo "Authenticating to gcloud"
  gcloud auth activate-service-account --key-file "$GOOGLE_SERVICE_ACCOUNT_FILE"
}

function delete_all() {
  delete_sql_instance
  delete_deployed_VMs
}

delete_all
