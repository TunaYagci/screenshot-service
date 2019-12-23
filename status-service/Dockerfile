FROM openjdk:8-jre-alpine
COPY dist/app.jar .
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-jar","/app.jar"]
