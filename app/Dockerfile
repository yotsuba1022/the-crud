## Stage 1: Build the application
FROM maven:3.5-jdk-11 as build

# Set the current working directory inside the image
WORKDIR /app

# Copy pom.xml to the image
COPY pom.xml .

# Copy the project source
COPY src src

# Package the application
RUN mvn clean test package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

## Stage 2: The minimal docker image with command to run the application
FROM openjdk:11-jdk-slim

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Do not forget to specify the active profile for spring boot
ENTRYPOINT ["java","-Dspring.profiles.active=container","-cp","app:app/lib/*","idv.clu.the.crud.TheCrudApplication"]
