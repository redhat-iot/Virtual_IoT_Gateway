package com.redhat.demo.iot.datacenter.monitor;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dataSet")
@XmlType(propOrder = { "timestamp", "deviceType", "deviceID", "payload","required","average", "errorCode", "errorMessage" })
public class DataSet {
	private String	timestamp;
	private String	deviceType;
	private int		deviceID;	
	private	int		payload;
	private int		required;
	private	float	average;
	private String 	errorMessage;
	private int  	errorCode;
	
	public DataSet()
	{
		this.timestamp 	= "";
		this.deviceType = "";
		this.deviceID	= 0;
		this.payload	= 0;
		this.required	= 0;
		this.average	= 0;
	}
	
	public DataSet(String time, String devType, int devID, int pay, int required, float average)
	{
		this.timestamp 	= time;
		this.deviceType = devType;
		this.deviceID	= devID;
		this.payload	= pay;
		this.required	= required;
		this.average	= average;
	}

	/**
	 * @return the required
	 */
	public int getRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(int required) {
		this.required = required;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceID
	 */
	public int getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the payload
	 */
	public int getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(int payload) {
		this.payload = payload;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}
	
}
	