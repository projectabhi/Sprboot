package com.example.processor;

import java.util.Timer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.multicast.threads.StudentThread;

@Component
public class StudentThread1 implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		StudentThread studentThread = new StudentThread("StudentThread1");

		Timer t = new Timer();
		//t.schedule(studentThread, 1000);
		studentThread.run();
		//int i=1;
			
		//Map<String, Object> map = exchange.getIn().getBody(Map.class);
		//map.put("processor2", new Integer(1));
		exchange.setProperty("processor2", new Integer(1));

		//Message msg = exchange.getIn();
		//msg.setBody(map, Map.class);

	}

}
