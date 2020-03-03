# Stage 1: download the code of github
FROM alpine/git as downloadSourceCode
WORKDIR /app
RUN git clone https://github.com/mixaverros88/docker-with-java-and-mysql

# Stage 2: spin up a mysql container
FROM mysql
# Add a database
ENV MYSQL_DATABASE company
# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
COPY --from=downloadSourceCode /app/docker-with-java-and-mysql/mysql/sql-scripts/ /docker-entrypoint-initdb.d/
