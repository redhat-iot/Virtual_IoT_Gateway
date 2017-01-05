#!/bin/bash

export SOURCE_AMQ_BROKER="tcp://localhost:61616"
export SOURCE_QUEUE="message.to.rules"
export SOURCE_BROKER_ADMIN_UID="admin"
export SOURCE_BROKER_ADMIN_PASSWD="change12_me"
export TARGET_AMQ_BROKER="tcp://localhost:61616"
export TARGET_QUEUE="message.to.datacenter"
export TARGET_BROKER_ADMIN_UID="admin"
export TARGET_BROKER_ADMIN_PASSWD="change12_me"

echo "Checking if we already have a running version"
 
if ps ax | grep -v grep | grep BusinessRulesService > /dev/null
then
    echo " ... we do, let's stop it."
    kill -9 `ps aux | grep BusinessRulesService | grep -v grep | awk '{print $2}'`
else
    echo " ... no we don't"
fi

echo "Let's build a current version"
pushd ./
cd BusinessRulesService
mvn clean install
popd

echo "Starting the rules service"
java -jar BusinessRulesService/target/rules-jar-with-dependencies.jar &
