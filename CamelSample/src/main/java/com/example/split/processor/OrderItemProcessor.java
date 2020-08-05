package com.example.split.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.split.to.Order;

@Component
public class OrderItemProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Order order=exchange.getIn().getBody(Order.class);
		System.out.println("Processing item:"+order.getItemName());
		order.setProcessed(true);
	}

}
