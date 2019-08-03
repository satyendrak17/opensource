#!/usr/bin/env bash
sudo chmod 777 -R .

sudo apt update
sudo apt install maven -y

sudo mvn clean install

echo "Launching warehouse application"
sudo java -jar target/assesment-0.0.1-SNAPSHOT.jar