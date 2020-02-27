# Stage 1: download the code of github
FROM alpine/git as downloadSourceCode
WORKDIR /app
RUN git clone https://github.com/mixaverros88/java-e-commerce

# Stage 2: build the project via maven
FROM maven:3.5-jdk-8-alpine as packageSourceCode
WORKDIR /app
COPY --from=downloadSourceCode /app/java-e-commerce /app
RUN mvn package

# Stage 3: spin up a tomcat container
FROM jboss/wildfly:11.0.0.Final

COPY --from=packageSourceCode /app/customization /opt/jboss/wildfly/customization/
COPY --from=packageSourceCode /app/target/java-e-commerce.war /opt/jboss/wildfly/standalone/deployments/

USER jboss
# Expose the ports we're interested in
EXPOSE 8080

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
