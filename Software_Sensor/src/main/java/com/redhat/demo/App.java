package com.redhat.demo;



/**
 * Hello world!
 *
 */
public class App 
{
	
	// Default Values for message producer
    private static final String	DEFAULT_DEVICETYPE   	= "temperature";
    private static final String DEFAULT_DEVICEID     	= "1";
    private static final String DEFAULT_INITIALVALUE 	= "70";
    private static final String DEFAULT_COUNT 		 	= "1";
    private static final String DEFAULT_UNIT		 	= "C";
    private static final String DEFAULT_WAIT		 	= "1";
    private static final String DEFAULT_RECEIVER		= "localhost";
    private static final String DEFAULT_BROKER_UID		= "admin";
    private static final String DEFAULT_BROKER_PASSWD 	= "change12_me";

	 
    public static void main( String[] args ) throws Exception
    {
    	
    	DummyDataGenerator dummy = new DummyDataGenerator();
    	MqttProducer		   producer;
       
        String devType 	 	  = System.getProperty("deviceType", DEFAULT_DEVICETYPE);
        String devID		  = System.getProperty("deviceID", DEFAULT_DEVICEID);
        int initialValue 	  = Integer.parseInt(System.getProperty("initialValue", DEFAULT_INITIALVALUE));
        int count 		 	  = Integer.parseInt(System.getProperty("count", DEFAULT_COUNT));
        int waitTime 		  = Integer.parseInt(System.getProperty("waitTime", DEFAULT_WAIT));
        String unit			  = System.getProperty("payloadUnit", DEFAULT_UNIT);
        String brokerURLMQTT  = "tcp://" + System.getProperty("receiverURL",DEFAULT_RECEIVER) +  ":1883";
        String brokerUID 	  = System.getProperty("brokerUID", DEFAULT_BROKER_UID);
        String brokerPassword = System.getProperty("brokerPassword", DEFAULT_BROKER_PASSWD);
        
        
        dummy.createInitialDataSet(devType, devID, initialValue, unit); 
       	
    	producer = new MqttProducer(brokerURLMQTT, brokerUID, brokerPassword, "mqtt.receiver");
        
        int counter = 0;
        while ( counter < count ) {
			
    		System.out.println("Sending '"+dummy.getDataSetCSV()+"'");
    		producer.run( dummy.getDataSetCSV(), dummy.getDataSet().getDeviceType(), dummy.getDataSet().getDeviceID() );
        	
			dummy.updateDataSet();
			
			counter++;
			
			Thread.sleep ( waitTime * 1000 );
        }
		    
        producer.close();
    }
}
