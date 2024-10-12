# Use a multi-stage build to keep the final image small

# Stage 1: Build the application
FROM gradle:7.6.0-jdk17 as builder
WORKDIR /home/gradle/project
COPY . .
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/grh-backend-*.jar ./app.jar

# Expose the port which Spring Boot listens on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]