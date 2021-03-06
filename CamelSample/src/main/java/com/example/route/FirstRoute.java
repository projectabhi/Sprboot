package com.example.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.borokali.model.req.Student;
import com.borokali.model.res.StudentResponse;
import com.example.exception.CustomBusnsException;

@Component
public class FirstRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(CustomBusnsException.class).process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				CustomBusnsException exception=exchange.getProperty(Exchange.EXCEPTION_CAUGHT, CustomBusnsException.class);;
				System.out.println(exception.getErrorCode().getErrCode());
				System.out.println(exception.getMessage());
				
				StudentResponse response=new StudentResponse();
				response.setErrorCode(exception.getErrorCode().getErrCode());
				response.setErrMsg(exception.getMessage());
				
				exchange.getOut().setBody(response);
			}
		}).log("Received body: ${body}").handled(true).end();
		
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
		.post("/postStudentName").type(Student.class).to("direct:studentStepProcessor")
		.get("/hello").route().log("Hello get request").transform().simple("Hello ${header.name}, Welcome to Camel")
		.endRest();
//		Unicast processing
		/*
		 * from("direct:studentProcessor").to("studentProcessor")
		 * .to("studentManipulator")
		 * .to("studentResponsePopulator").log(LoggingLevel.INFO,"${body}")
		 */
		from("direct:studentStepProcessor").routeId("stepprocessing")
		    .to("studentProcessor")
		    .step("Step1")
		    	.to("studentStepProc1")
		    .end()
		    .step("Step2")
	    		.to("studentStepProc2")
	    	.end()
		   .to("studentResponsePopulator").log(LoggingLevel.INFO,"${body}");
		
		//Multicast processing
		from("direct:studentProcessor").streamCaching()
									   .to("studentProcessor")
									   .to("direct:studentMulticast")
									   .to("studentResponsePopulator").log(LoggingLevel.INFO,"${body}");
		from("direct:studentMulticast").multicast()
									   .parallelProcessing().executorService(executor)
									   .to("seda:studentThread1","seda:studentThread2")
									   .parallelAggregate().aggregationStrategyRef("studentAggregator").stopOnException()
									   .end();
		from("seda:studentThread1").to("studentThread1");
		from("seda:studentThread2").to("studentThread2");
	}

}
