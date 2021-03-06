package com.example.demo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.example.*")
@EntityScan(basePackages = "com.example.persitence.model")
@EnableJpaRepositories("com.example.persitence.repo")
//@EnableAutoConfiguration
public class CamelSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelSampleApplication.class, args);
		System.out.println("hi");
	}

	@Bean
	public ServletRegistrationBean camelServletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				"/CamelSample/*");
		registrationBean.setName("CamelServlet");
		registrationBean.setLoadOnStartup(2);
		
		return registrationBean;
	}
}
