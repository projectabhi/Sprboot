package com.example.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;
import com.example.persitence.model.Student;
import com.example.persitence.repo.StudentRepo;

@Component
public class StudentResponsePopulator implements Processor{

	private static final Logger log=LogManager.getLogger(StudentResponsePopulator.class);
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		log.info("Inside StudentResponsePopulator");
		
		log.info("Getting response object from exchange which was set in aggregator");
		StudentResponse response=(StudentResponse) exchange.getProperty("response");
		response.setAddr("TestAddr");
		
		log.info("Getting property from first endpoint:"+exchange.getProperty("processor1"));
		int result=(int)exchange.getProperty("processor1")+response.getRollNo();
		
		log.info("Getting property from subsequent endpoint...");
		if(exchange.getProperty("processor2") != null)
			result = result+(int)exchange.getProperty("processor2");
		if(exchange.getProperty("processor3") != null)
			result = result+(int)exchange.getProperty("processor3");
				
		response.setRollNo(result);
		
		List<Student> studList=(List<Student>)studentRepo.findAll();
		log.info("Student List:"+studList);
		
		log.info("Setting response...");
		Message msg=exchange.getOut();
		msg.setBody(response, StudentResponse.class);
	}

}
