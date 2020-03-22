package com.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class StudentProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.getIn().getBody());
		exchange.getOut().setBody("Hi");
	}

}
