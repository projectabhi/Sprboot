package com.example.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;

@Component
public class StudentProcessor implements Processor{
	private static final Logger log=LogManager.getLogger(StudentProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info(exchange.getIn().getBody());
		
		/*
		 * Map<String, Object> map=new HashMap<String, Object>(); map.put("processor1",
		 * new Integer(1));
		 * 
		 * Message msg=exchange.getIn(); msg.setBody(map, Map.class);
		 */
		
		exchange.setProperty("processor1", new Integer(1));
		StudentResponse response=new StudentResponse();
		
		exchange.setProperty("response",response);
		
	}

}
