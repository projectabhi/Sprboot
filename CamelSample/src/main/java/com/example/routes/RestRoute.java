package com.example.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration()
		.component("servlet")
		// and output using pretty print
        .dataFormatProperty("prettyPrint", "true")
		.bindingMode(RestBindingMode.json)
		.contextPath("CamelSample").port(8080);;
		
		rest("/test")
		.produces("application/json")
		.consumes("application/json")
		.get("/hello")
		.to("direct:hello");
		
		from("direct:hello").log("GET INVOKED").transform().constant("Hello world, Camel is working");
		
		
	}

}
