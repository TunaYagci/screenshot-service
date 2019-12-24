#!/usr/bin/env bash
mvn clean install -DskipTests
cp target/*.jar dist/app.jar
