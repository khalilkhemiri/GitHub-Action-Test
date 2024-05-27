FROM openjdk:17-alpine
EXPOSE 8089
ADD targer/demo88.jar demo88.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
