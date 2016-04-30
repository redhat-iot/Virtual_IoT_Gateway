#!/bin/bash

export SOURCE_AMQ_BROKER="tcp://receiver:61616"
export SOURCE_QUEUE="message.to.rules"
export SOURCE_BROKER_ADMIN_UID="admin"
export SOURCE_BROKER_ADMIN_PASSWD="change12_me"
export TARGET_AMQ_BROKER="tcp://receiver:61616"
export TARGET_QUEUE="message.to.datacenter"
export TARGET_BROKER_ADMIN_UID="admin"
export TARGET_BROKER_ADMIN_PASSWD="change12_me"

echo "Starting the rules service"
java -jar BusinessRulesService/target/rules-jar-with-dependencies.jar
