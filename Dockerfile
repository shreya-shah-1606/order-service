# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

EXPOSE 8082

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your host into the container at the working directory
COPY target/order-0.0.1-SNAPSHOT.jar /app/

# Run the application using "java -jar"
CMD ["java", "-jar", "order-0.0.1-SNAPSHOT.jar"]
