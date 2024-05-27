FROM openjdk:17-alpine
EXPOSE 8089
ADD spring-github-action.jar spring-github-action.jar
ENTRYPOINT ["java", "-jar", "/spring-github-action.jar"]
