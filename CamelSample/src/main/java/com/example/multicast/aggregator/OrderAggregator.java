package com.example.multicast.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.example.split.to.Order;

@Component
public class OrderAggregator implements AggregationStrategy {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderAggregator.class);

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			// the first time we aggregate we only have the new exchange,
			// so we just return it
			return newExchange;
		}
		Order order=oldExchange.getIn().getBody(Order.class);
		Order newOrder=newExchange.getIn().getBody(Order.class);
		
		log.info("Aggregate old orders: " + order);
		log.info("Aggregate new order: " + newOrder);
		
		return null;
	}

}
