#!/bin/bash

export deviceType="temperature"
export deviceID="4711"
export initialValue="20"
export count="50"
export waitTime="1"
export receiverURL="localhost"
export brokerUID="admin"
export brokerPassword="change12_me"
export highWater="800"
export lowWater="200";

echo "Starting the producer to send messages "
java -DhighWater=$highWater -DlowWater=$lowWater -DbrokerUID=$brokerUID -DbrokerPassword=$brokerPassword -DreceiverURL=$receiverURL -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar Software_Sensor/target/softwareSensor-jar-with-dependencies.jar
