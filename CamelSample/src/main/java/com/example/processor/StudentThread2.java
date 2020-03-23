package com.example.processor;

import java.util.Timer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.multicast.threads.StudentThread;

@Component
public class StudentThread2 implements Processor {

	private static final Logger log=LogManager.getLogger(StudentThread2.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		StudentThread studentThread = new StudentThread("StudentThread2");
		Timer t = new Timer();
		//t.schedule(studentThread, 1000);
		studentThread.run();
		/*
		 * Map<String, Object> map = exchange.getIn().getBody(Map.class);
		 * map.put("processor3", new Integer(1));
		 * 
		 * Message msg = exchange.getIn(); msg.setBody(map, Map.class);
		 */
		exchange.setProperty("processor3", new Integer(1));
	}

}
