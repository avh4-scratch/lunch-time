#!/bin/bash -ex

export JAVA_HOME=$(/usr/libexec/java_home 1.7)
mvn clean package
SPRING_PROFILES_ACTIVE=dev java -jar ./target/lunch-time.jar
