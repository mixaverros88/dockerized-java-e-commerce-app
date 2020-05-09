#!/usr/bin/env bash
#Delete running containers
docker rm $(docker ps -a -q ) -f

#Delete network 
docker network rm my_isolated_bridge_network

# Create Network
docker network create --driver bridge my_isolated_bridge_network

#mysql
docker build -t mixaverross88/java-e-commerce-mysql -f Dockerfile.Mysql .

docker run -d -p 3306:3306 --name mysqlapp \
--network=my_isolated_bridge_network \
-e MYSQL_ROOT_PASSWORD='M!xalis1029' mixaverross88/java-e-commerce-mysql

#java
docker build -t mixaverross88/java-e-commerce:3.0 . 

docker run -p 8080:8080 -p 9990:9990 --name javaapp \
--network=my_isolated_bridge_network \
mixaverross88/java-e-commerce:3.0



