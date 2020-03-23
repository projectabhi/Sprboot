package com.example.multicast.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;

@Component
public class StudentAggregator implements AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		System.out.println(newExchange.getProperty("processor2")+":"+newExchange.getProperty("processor3"));
		int i = 0,j=0,k =0;
		if(newExchange.getProperty("processor2") != null)
			i=(int)newExchange.getProperty("processor2");
		if(newExchange.getProperty("processor3") != null)
			j=(int)newExchange.getProperty("processor3");
		
		k = i+j;
		StudentResponse response=new StudentResponse();
		response.setRollNo(k);
		response.setAddr("Test");
		
		newExchange.getOut().setBody(response, StudentResponse.class);
		System.out.println(oldExchange);
		return null;
	}

}
