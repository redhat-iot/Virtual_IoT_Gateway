#!/bin/bash

export SOURCE_AMQ_BROKER="tcp://localhost:61616"
export SOURCE_BROKER_ADMIN_UID="admin"
export SOURCE_BROKER_ADMIN_PASSWD="change12_me"

echo "Let's build a current version"
pushd ./
cd RoutingService
mvn clean install
popd

echo "Deploy the routing service"
cp RoutingService/target/routingService-1.0.0.jar ../fuse/deploy/
