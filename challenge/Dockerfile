FROM openjdk:17-jdk

# Argument for the JAR
ARG JAR_FILE=build/libs/*.jar

# Copy the JAR inside the container
COPY ${JAR_FILE} app.jar

# Command to execute the app
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 5005

