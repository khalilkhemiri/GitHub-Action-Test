FROM openjdk:17-alpine

EXPOSE 8089

# Add the JAR file directly
ADD target/spring-github-action.jar spring-github-action.jar

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "/spring-github-action.jar"]
