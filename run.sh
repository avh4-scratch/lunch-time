#!/bin/bash -ex

mvn clean package
SPRING_PROFILES_ACTIVE=dev java -jar ./target/lunch-time.jar
