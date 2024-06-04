# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build

# Copy the source code and the Maven configuration file to the Docker image
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

# Change working directory
WORKDIR /usr/src/app

# Build the application using Maven, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM openjdk:17-jdk-slim

# Copy the built application from the first stage to the second stage
COPY --from=build /usr/src/app/target/mfa-0.0.1-SNAPSHOT.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]