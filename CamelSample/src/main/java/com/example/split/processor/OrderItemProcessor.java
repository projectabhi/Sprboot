package com.example.split.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.split.to.Order;

@Component
public class OrderItemProcessor implements Processor {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderItemProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Order order=exchange.getIn().getBody(Order.class);
		log.info("Processing item:"+order.getItemName());
		order.setProcessed(true);
	}

}
