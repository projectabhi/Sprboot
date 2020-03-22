package com.example.config;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelSampleConfig {

	@Autowired
	CamelContext camelContext;
}
