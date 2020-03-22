package com.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class FirstRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class).log("Exception retry...").maximumRedeliveries(3).maximumRedeliveryDelay(2000);
		//from("timer:foo").to("log:bar");
		/*
		 * from("file:C:/inputFolder?noop=true").process(new Processor() {
		 * 
		 * @Override public void process(Exchange exchange) throws Exception {
		 * System.out.println("Inside processor..."); throw new
		 * Exception("Throwing exception...");
		 * 
		 * } }).to("file:C:/outputFolder");
		 */
		restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json);
		
		
		rest().get("/hello").route().transform().simple("Hello ${header.name}, Welcome to Camel")
		.endRest();
//	 
//	    from("direct:hello")
//	      .log(LoggingLevel.INFO, "Hello World")
//	      .transform().simple("Hello World");
	}

}
