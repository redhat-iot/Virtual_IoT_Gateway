package com.redhat.demo.businessRules;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.apache.commons.io.IOUtils;

public class App 
{
    private static final Logger log = Logger.getLogger(BRMSServer.class.getName());
	
   
   	 
    public static void main( String[] args ) throws InterruptedException, JMSException, JAXBException, MqttPersistenceException, MqttException
    {
    	String 	messageFromQueue;
    	
    	// definition of source Broker and queues
    	String 	sourceAMQBroker  		= System.getenv("SOURCE_AMQ_BROKER");
    	String 	sourceQueue 	 		= System.getenv("SOURCE_QUEUE");
    	String  sourceBrokerUID		 	= System.getenv("SOURCE_BROKER_ADMIN_UID");
    	String  sourceBrokerPassword   	= System.getenv("SOURCE_BROKER_ADMIN_PASSWD");
    	
    	// definition of target Broker and queues
    	String 	targetAMQBroker 		= System.getenv("TARGET_AMQ_BROKER");
    	String 	targetQueue 	 		= System.getenv("TARGET_QUEUE");
    	String  targetBrokerUID		 	= System.getenv("TARGET_BROKER_ADMIN_UID");
    	String  targetBrokerPassword   	= System.getenv("TARGET_BROKER_ADMIN_PASSWD");
    	
    	
    	System.out.println(" Check if needed AMQ-Broker are already available");
    	AMQTester tester = new AMQTester(); 
    	tester.waitForBroker(sourceAMQBroker);
    	tester.waitForBroker(targetAMQBroker);
    	
    	System.out.println(" AMQ-Broker " + sourceAMQBroker + " ready to work! ");
    	System.out.println(" AMQ-Broker " + targetAMQBroker + " ready to work! ");
    	
    	
		Consumer consumer = new Consumer(sourceBrokerUID, sourceBrokerPassword, sourceQueue, sourceAMQBroker);
		
		Producer producer = new Producer(targetBrokerUID, targetBrokerPassword, targetQueue, targetAMQBroker);
	
		BRMSServer brmsServer = new BRMSServer();
		
		while ( true ) {
			messageFromQueue = consumer.run(2000);		
			
			if ( messageFromQueue != null ) {
				
	            // Convert TextMessage to DataSet via jaxb unmarshalling
	            JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
	            StringReader reader = new StringReader( messageFromQueue );
	            DataSet event = (DataSet) unmarshaller.unmarshal(reader);
		
	            event.setRequired(0);	
	            event.setErrorCode(0);
	            
	            System.out.println("Device-Type = " + event.getDeviceType());
	            System.out.println("Device-ID   = " + event.getDeviceID());
	            System.out.println("Payload     = " + event.getPayload());
	         
            	event = brmsServer.insert( event);
            	
            	System.out.println("Result      = " + event.getErrorCode());
            	System.out.println("----------------------");
                         
	            if ( event.getErrorCode() != 0 ) {
	            	
					producer.run( event.asString() );
					System.out.println("----------------------");

	            }
			}
		}
    } 
}
