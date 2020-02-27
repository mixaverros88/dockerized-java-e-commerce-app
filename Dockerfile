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
ADD standalone.xml /opt/jboss/wildfly/standalone/configuration
COPY --from=packageSourceCode /app/target/java-e-commerce.war /opt/jboss/wildfly/exploded

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true
USER jboss
# Expose the ports we're interested in
EXPOSE 8080

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
