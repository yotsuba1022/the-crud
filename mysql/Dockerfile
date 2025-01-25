# Use mysql:8.0.33 as the base image
FROM mysql:8.0.33

# Install time zone related files
RUN apt-get update && apt-get install -y tzdata

# Copy SQL scripts to the auto-run directory
COPY ./init_sql_scripts/*.sql /docker-entrypoint-initdb.d/

# Optional: Set execution permissions for scripts (in case of custom scripts)
RUN chmod 644 /docker-entrypoint-initdb.d/*.sql
