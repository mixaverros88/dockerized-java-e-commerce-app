# Stage 1: download the code of github
FROM alpine/git as downloadSourceCode
WORKDIR /app
RUN git clone https://github.com/mixaverros88/dockerized-java-e-commerce-app

# Stage 2: build the project via maven
FROM maven:3.5-jdk-8-alpine as packageSourceCode
WORKDIR /app
COPY --from=downloadSourceCode /app/dockerized-java-e-commerce-app /app
RUN mvn package

# WildFly 8 on Docker with Centos 7 and OpenJDK 1.7
FROM jboss/wildfly:11.0.0.Final

MAINTAINER Mike Verros "mixalisverros@hotmail.gr"

# ENV VARIABLES
ENV WILDFLY_HOME /opt/jboss/wildfly
ENV WILDFLY_VERSION 11.0.0.Final
ENV MYSQL_HOST mysqlapp:3311
ENV MYSQL_USER root
ENV MYSQL_PASSWORD M!xalis1029
ENV MYSQL_DATABASE poll

# Add standalone xml file
COPY customization/verros-standalone.xml ${WILDFLY_HOME}/standalone/configuration/verros-standalone.xml
# Get MySQL driver
COPY customization/mysql-connector-java-8.0.19.jar ${WILDFLY_HOME}/modules/com/mysql/jdbc/main/mysql-connector-java-8.0.19.jar
# MYSQL JDBC Module
COPY customization/module.xml ${WILDFLY_HOME}/modules/com/mysql/jdbc/main/module.xml
# Add console admin user
RUN ${WILDFLY_HOME}/bin/add-user.sh admin adminPassword  --silent
# Ports
EXPOSE 8080 9990
# Volumes
VOLUME ${WILDFLY_HOME}/standalone/deployments/
VOLUME ${WILDFLY_HOME}/standalone/log/
# RUN script
COPY customization/start-wildfly.sh ${WILDFLY_HOME}/bin/start-wildfly.sh
#Deploy
COPY --from=packageSourceCode /app/target/java-e-commerce.war ${DEPLOYMENT_DIR}
USER root
RUN chmod +x ${WILDFLY_HOME}/bin/start-wildfly.sh
#USER jboss

ENTRYPOINT ["sh", "-c", "${WILDFLY_HOME}/bin/start-wildfly.sh"]