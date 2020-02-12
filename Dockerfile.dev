# a lightweight linux distribution
FROM tomcat:8.0-alpine

# maintainer
LABEL maintainer="mixalisverros@hotmail.gr"

# the port which the tomcat is listening inside the container
EXPOSE 8080

# run the tomcat
CMD ["catalina.sh", "run"]

# add a healthvheck
HEALTHCHECK CMD curl --fail http://localhost:80/webapi/ || exit 1
