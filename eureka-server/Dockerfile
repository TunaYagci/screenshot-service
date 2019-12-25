FROM openjdk:8-jre-alpine
MAINTAINER tunayagci
COPY dist/app.jar .
COPY wait-for-it.sh .
RUN chmod +x /wait-for-it.sh
RUN apk add bash
CMD ./wait-for-it.sh -t 60 kafka:9092 -- java $JAVA_OPTS -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar app.jar
