package com.example.multicast.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class StudentAggregator implements AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		System.out.println(newExchange.getProperty("processor2")+":"+newExchange.getProperty("processor3"));
		System.out.println(oldExchange.getIn().getBody());
		return null;
	}

}
