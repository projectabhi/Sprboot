package com.example.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.borokali.model.req.Student;

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
		ExecutorService executor = Executors.newFixedThreadPool(16);
		
		restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json);
		
		
		rest().produces("application/json")
		.post("/postStudent").type(Student.class).to("direct:studentProcessor")
		.get("/hello").route().transform().simple("Hello ${header.name}, Welcome to Camel")
		.endRest();
		//Unicast processing
		/*
		 * from("direct:studentProcessor").to("studentProcessor")
		 * .to("studentManipulator")
		 * .to("studentResponsePopulator").log(LoggingLevel.INFO,"${body}")
		 */
		from("direct:studentProcessor").streamCaching()/* .to("studentProcessor") */
										.to("direct:studentMulticast").log(LoggingLevel.INFO,"${body}");
		from("direct:studentMulticast").multicast()
										.parallelProcessing().executorService(executor)
										.to("seda:studentThread1")
										.to("seda:studentThread2")
										.parallelAggregate().aggregationStrategyRef("studentAggregator")
										.end();
		from("seda:studentThread1").to("studentThread1");
		from("seda:studentThread2").to("studentThread2");
	}

}
