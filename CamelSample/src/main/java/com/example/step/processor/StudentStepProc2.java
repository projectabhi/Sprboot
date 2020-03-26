package com.example.step.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class StudentStepProc2 implements Processor {

	private static final Logger log=LogManager.getLogger(StudentStepProc2.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.setProperty("processor3", new Integer(1));
	}

}
