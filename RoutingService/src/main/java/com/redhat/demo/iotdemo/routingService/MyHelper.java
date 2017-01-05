
package com.redhat.demo.iotdemo.routingService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

public class MyHelper {
	
	@Handler
	public String enhanceMessage( String body,  Exchange exchange  ) {
		String res = null;
		
		res = addDeviceID(body, exchange);
		res = addDeviceType(res, exchange);
		res = appendTimestamp(res, exchange);

		return res;
	}
   
    @Handler
    public String appendTimestamp( String body,  Exchange exchange ) {
       
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS");
        Date timeNow = new Date();

        body = body +","+format.format(timeNow);
             
        return body;
       
    }

   
    @Handler
    public String addDeviceType(String body, Exchange exchange) {
       
        String result=null;
       
        Pattern pattern = Pattern.compile("(?<=\\/)(.*?)(?=\\/)");
        Matcher matcher = pattern.matcher((String)exchange.getIn().getHeader("CamelMQTTSubscribeTopic"));
        if (matcher.find())
        {
            result = matcher.group(0);
        }
       
        return result + ", " + body;
       
    }
   

    @Handler
    public String addDeviceID(String body,  Exchange exchange) {
       
        String result=null;
       
        Pattern pattern = Pattern.compile("([^\\/]*)$");
        Matcher matcher = pattern.matcher((String)exchange.getIn().getHeader("CamelMQTTSubscribeTopic"));
        if (matcher.find())
        {
            result = matcher.group(0);
        }
             
        return result + ", " + body;
    }

}

