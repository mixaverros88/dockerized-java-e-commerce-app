#!/bin/sh

${WILDFLY_HOME}/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c standalone-verros.xml \
	-Dmysql.host=${MYSQL_HOST} -Dmysql.username=${MYSQL_USER} -Dmysql.password=${MYSQL_PASSWORD} -Dmysql.database=${MYSQL_DATABASE}\