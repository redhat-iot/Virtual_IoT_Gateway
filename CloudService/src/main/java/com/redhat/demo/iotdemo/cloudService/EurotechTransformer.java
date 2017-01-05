
package com.redhat.demo.iotdemo.cloudService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.eclipse.kura.message.KuraPayload;

public class EurotechTransformer {
	
	@Handler
    public void transform(String body,  Exchange exchange) throws Exception {
        KuraPayload payload = new KuraPayload();
        
        payload.addMetric("temperature", new Random().nextInt(20));
        
        exchange.getIn().setBody(payload);
    }
}

