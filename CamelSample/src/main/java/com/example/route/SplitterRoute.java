package com.example.route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.stereotype.Component;

import com.example.split.to.Order;

@Component
public class SplitterRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		ExecutorService threadPool=Executors.newFixedThreadPool(4);
		
		from("timer://generateOrders?fixedRate=true&period=10000")
        .log("Generate order items")
        .process(new Processor() {
             
            public void process(Exchange exchange) throws Exception {
            	Order order1=new Order("Item1", 2, 1000,false);
            	Order order2=new Order("Item2", 2, 1000,false);
            	Order order3=new Order("Item3", 2, 1000,false);
            	Order order4=new Order("Item4", 2, 1000,false);
                List<Order> orders=new ArrayList<Order>();
                orders.add(order1);
                orders.add(order2);
                orders.add(order3);
                orders.add(order4);
                exchange.setProperty("order", orders);
            }
        })
        .to("direct:processOrder");
         
        from("direct:processOrder")
        .log("Process order ${exchangeProperty.order}")
        .split(simple("${exchangeProperty.order}")).parallelProcessing().executorService(threadPool)
        .to("direct:processOrderItem")
        .end()
        .log("Order processed: ${exchangeProperty.order}");
         
        from("direct:processOrderItem")
        //.log("Processing item ${exchangeProperty.order.itemName}")
        .process("orderItemProcessor");		
	}

}
