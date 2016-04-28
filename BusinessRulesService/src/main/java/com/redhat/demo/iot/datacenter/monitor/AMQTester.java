package com.redhat.demo.iot.datacenter.monitor;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AMQTester {

	private ActiveMQConnectionFactory 	connectionFactory;
	private	Connection				  	connection;
	
	public AMQTester( ) {
	}

	public void waitForBroker( String brokerURL ){
		while( testAvailability( brokerURL ) == false ) {
    		System.out.println(" AMQ-Broker " + brokerURL + " not yet available ");

    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}

    	System.out.println(" AMQ-Broker " + brokerURL + " ready to work! ");

	}
	
	public boolean testAvailability( String brokerURL) {
		boolean res = false;
		
        try {
        	// Create a ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory("admin", "change12_me", brokerURL);

            // Create a Connection
            connection = connectionFactory.createConnection();
        	
			connection.start();
			
			connection.close();
			
			res = true;
		} catch (JMSException e) {
			
			res = false;
			
		}
		
		return res;
	}
}
