#!/bin/bash

export SOURCE_AMQ_BROKER="tcp://localhost:61616"
export SOURCE_QUEUE="message.to.rules"
export SOURCE_BROKER_ADMIN_UID="admin"
export SOURCE_BROKER_ADMIN_PASSWD="change12_me"
export TARGET_AMQ_BROKER="tcp://localhost:61616"
export TARGET_QUEUE="message.to.datacenter"
export TARGET_BROKER_ADMIN_UID="admin"
export TARGET_BROKER_ADMIN_PASSWD="change12_me"

echo "Checking if we already have a running instance of JBoss Fuse"
 
if ps ax | grep -v grep | grep karaf > /dev/null
then
    echo "... we do!"
    echo "Please manually terminate the running instance of JBoss Fuse!"
    exit 0 
else
    echo "... no we don't"
    echo "Let's copy some configurations."
    cp config/users.properties ../fuse/etc
    cp config/activemq.xml ../fuse/etc
    echo "Start JBoss Fuse"
    ../fuse/bin/fuse
fi

