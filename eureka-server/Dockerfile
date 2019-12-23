FROM openjdk:8-jre-alpine
COPY dist/app.jar .
COPY wait-for-it.sh .
RUN chmod +x /wait-for-it.sh
RUN apk add bash
ENTRYPOINT ["./wait-for-it.sh", "-t", "60", "kafka:9092", "--", "java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=production", "-jar","/app.jar"]
