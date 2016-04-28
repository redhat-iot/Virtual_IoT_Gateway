package com.redhat.demo;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
 
public class DummyDataGenerator {

	private DataSet tempSet;
	
	public void createInitialDataSet(String  devType, String devID, int pay, String unit ) {
		tempSet = new DataSet();
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS");
		Date timeNow = new Date();
		
		tempSet.setTimestamp( format.format(timeNow) );
		tempSet.setDeviceType(devType);
		tempSet.setDeviceID(devID);
		tempSet.setPayload(pay);
		tempSet.setUnit(unit);
		tempSet.setCount(0);
	}
	
	public void updateDataSet() {
		Random random = new Random();

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS");
		Date timeNow = new Date();
		
		tempSet.setTimestamp( format.format(timeNow) );
		
		int randValue = random.nextInt(1000);
		
		tempSet.setCount( tempSet.getCount() + 1 );
		
		if ( randValue <= 200 )
			tempSet.setPayload(tempSet.getPayload()+1);
		else if ( randValue >= 800 )
			tempSet.setPayload(tempSet.getPayload()-1);
	}
	
	public DataSet getDataSet(){
		return tempSet;
	}
	
	public String getDataSetXML() {
		return jaxbObjectToXML();
	}
	
	public String getDataSetCSV() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( tempSet.getPayload() ).append(",");
		sb.append( tempSet.getCount() );
		
		return sb.toString();
	}
	
	private String jaxbObjectToXML( ) {
		 
		StringWriter writer = new StringWriter();
        
        try {
            JAXBContext context = JAXBContext.newInstance(DataSet.class);
            
            Marshaller m = context.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            m.marshal(this.getDataSet(), writer);
           
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return writer.toString();
	}
	
}