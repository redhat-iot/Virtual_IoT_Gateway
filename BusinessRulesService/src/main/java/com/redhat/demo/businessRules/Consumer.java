package com.redhat.demo.businessRules;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer  implements ExceptionListener  {

	private ActiveMQConnectionFactory 	connectionFactory;
	private	Connection				  	connection;
	private Session						session;
	private Destination					destination;
	private MessageConsumer				consumer;
	
	public Consumer(String user, String passwd, String queueName, String brokerURL) throws JMSException {
	     // Create a ConnectionFactory
        connectionFactory = new ActiveMQConnectionFactory(user, passwd, brokerURL);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(this);

        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        destination = session.createQueue(queueName);

        // Create a MessageConsumer from the Session to the Topic or Queue
        consumer = session.createConsumer(destination);

	}
	
	 public String run(int waitTimeout) {
		 TextMessage 	textMessage;
		 String 		text=null;
		 
         try {
             // Wait for a message
             Message message = consumer.receive(waitTimeout);

             if (message instanceof TextMessage) {
                 textMessage = (TextMessage) message;
                 text = textMessage.getText();
             } 
         } catch (Exception e) {
             System.out.println("Caught: " + e);
             e.printStackTrace();
         }
         
         return text;
     }

	 public void close() throws JMSException {

         consumer.close();
         session.close();
         connection.close();

	 }

	public void onException(JMSException arg0) {
		// TODO Auto-generated method stub
		
	}
}
