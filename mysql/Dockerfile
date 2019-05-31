# Use mysql:latest as the basic image
FROM mysql:latest

# Author
MAINTAINER clu <yotsuba1022@gmail.com>

# Definition of the directory which will be executed by the container automatically
ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

# Definition of the working directory in docker container
ENV WORK_PATH /usr/local/work

# Definition of SQL command documents
ENV INIT_DB_SQL      init_database.sql
ENV INIT_TABLE_SQL   init_table.sql
ENV INIT_USER_SQL    init_user.sql
ENV INIT_DATA_SQL    init_data.sql

# Definition of shell scripts
ENV INIT_MYSQL_SHELL init_mysql.sh

# Create the working directory in docker container
RUN mkdir -p $WORK_PATH

# Copy all SQL command documents under the working directory
COPY ./init_sql_scripts/$INIT_DB_SQL    $WORK_PATH/
COPY ./init_sql_scripts/$INIT_TABLE_SQL $WORK_PATH/
COPY ./init_sql_scripts/$INIT_USER_SQL  $WORK_PATH/
COPY ./init_sql_scripts/$INIT_DATA_SQL  $WORK_PATH/

# Copy the shell scripts under $AUTO_RUN_DIR so the container can execute those scripts
COPY ./init_shell_scripts/$INIT_MYSQL_SHELL $AUTO_RUN_DIR/

# Grant executable permission to the documents
RUN chmod a+x $AUTO_RUN_DIR/$INIT_MYSQL_SHELL
