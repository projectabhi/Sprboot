package com.example.multicast.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;
import com.example.processor.StudentThread2;

@Component
public class StudentAggregator implements AggregationStrategy{
	
	private static final Logger log=LogManager.getLogger(StudentAggregator.class);

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		log.info(newExchange.getProperty("processor2")+":"+newExchange.getProperty("processor3"));
		int i = 0,j=0,k =0;
		if(newExchange.getProperty("processor2") != null)
			i=(int)newExchange.getProperty("processor2");
		if(newExchange.getProperty("processor3") != null)
			j=(int)newExchange.getProperty("processor3");
		
		k = i+j;
		StudentResponse response=new StudentResponse();
		response.setRollNo(k);
		response.setAddr("Test");
		
		newExchange.setProperty("response", response);
		log.info("k:"+k+"~~response.rollno:"+response.getRollNo());
		return newExchange;
	}

}
