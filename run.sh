#!/usr/bin/env bash

# Create Network
docker network create --driver bridge my_isolated_bridge_network

# Build Mysql Image
docker build -t mixaverross88/java-e-commerce-mysql:1.1 -f Dockerfile.Mysql .

# Run Mysql Image
docker run -d -p 3311:3306 --name mysqlapp \
--network=my_isolated_bridge_network \
-e MYSQL_ROOT_PASSWORD=kdiosk33 mixaverross88/java-e-commerce-mysql:1.1

# Build java app
docker build -t mixaverross88/java-e-commerce:3.1 .

# Run Java Image
docker run -p 8080:8080 -p 9990:9990 --name javaapp \
--network=my_isolated_bridge_network \
mixaverross88/java-e-commerce:3.1



