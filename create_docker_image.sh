#!/bin/bash

mvn clean package

echo "----------------------------------"
echo "|Build docker image bookstore-app|"
echo "----------------------------------"
cd "target/container-files-app/container-files/bookstore"
docker build -t bookstore-app . -f Dockerfile