package com.example.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class StudentManipulator implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Inside StudentManipulator");
		
		Map<String, Object> map=exchange.getIn().getBody(Map.class);
		map.put("processor2", new Integer(1));
		
		Message msg=exchange.getIn();
		msg.setBody(map, Map.class);
		
	}

}
