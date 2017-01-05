package com.redhat.demo.businessRules;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	 private ConnectionFactory factory;
	 private Connection connection;
	 private Session session;
	 private MessageProducer producer;
 
	 public Producer(String user, String passwd, String queueName, String brokerURL) throws JMSException
	 {
		// setup the connection to ActiveMQ
	    factory = new ActiveMQConnectionFactory(user, passwd, brokerURL);
	    	
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        producer = session.createProducer(destination);
	 }
  	 
	 public void run(String data) throws JMSException
	 {
    
        Message message = session.createTextMessage( data );
        
        System.out.println("Sending " + data);
        
        producer.send(message);
	 }
 
	    public void close() throws JMSException
	    {
	        if (connection != null)
	        {
	            connection.close();
	        }
	    }
}
