#!/usr/bin/env bash
modules=( "eureka-server" "event-registry" "image-service" "scan-service" "screenshot-consumer" "status-service" "zuul-server" )
for i in "${modules[@]}"
do :
    cd ../$i
   # do whatever on $i
    mvn clean install -DskipTests
    cp target/*.jar dist/app.jar
done
