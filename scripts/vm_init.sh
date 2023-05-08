#!/bin/bash

DUCK_TOKEN=2836d713-b14a-404a-83ee-6d67c4f93d86
DUCK_DNS=youthcouncil
EMAIL=seifeldin.sabry@student.kdg.be

apt-get update && apt-get -y install openjdk-17-jdk
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
systemctl restart apache2
curl -k "https://www.duckdns.org/update?domains=$DUCK_DNS&token=$DUCK_TOKEN&ip="
