FROM jboss/wildfly:9.0.2.Final
MAINTAINER Mike-George Verros "mixalisverros@hotmail.gr"

# ENV VARIABLES 
ENV WILDFLY_HOME /opt/jboss/wildfly
ENV WILDFLY_VERSION 9.0.2.Final
ENV MYSQL_HOST mysqlapp
ENV MYSQL_USER root
ENV MYSQL_PASSWORD M!xalis1029
ENV MYSQL_DATABASE test_poll

# Add standalone xml file
COPY /customization/standalone.xml ${WILDFLY_HOME}/standalone/configuration/standalone.xml
# Get MySQL driver
ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar ${WILDFLY_HOME}/modules/com/mysql/jdbc/main/mysql-connector-java-5.1.39-bin.jar
# MYSQL JDBC Module
COPY /customization/module-mysql.xml ${WILDFLY_HOME}/modules/com/mysql/jdbc/main/module.xml
# Add console admin user
RUN ${WILDFLY_HOME}/bin/add-user.sh mikeverros M!ke8920  --silent
# Ports
EXPOSE 8080 9990
# Volumes
VOLUME ${WILDFLY_HOME}/standalone/deployments/
VOLUME ${WILDFLY_HOME}/standalone/log/
# RUN script
COPY /customization/start-wildfly.sh ${WILDFLY_HOME}/bin/start-wildfly.sh
USER root
RUN chmod +x ${WILDFLY_HOME}/bin/start-wildfly.sh
#USER jboss
COPY /target/java-e-commerce.war ${WILDFLY_HOME}/standalone/deployments/

ENTRYPOINT ["sh", "-c", "${WILDFLY_HOME}/bin/start-wildfly.sh"]