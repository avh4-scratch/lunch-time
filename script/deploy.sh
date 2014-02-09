#!/bin/bash -ex

#the above -e causes the script to exit if any command fails
#the above -x causes the script to print an execution trace 

export JAVA_HOME=$(/usr/libexec/java_home 1.7)
mvn clean package 
cf push
