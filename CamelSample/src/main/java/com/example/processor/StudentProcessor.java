package com.example.processor;

import java.util.List;
import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;
import com.example.persitence.model.Student;
import com.example.persitence.repo.StudentRepo;

@Component
public class StudentProcessor implements Processor{
	private static final Logger log=LogManager.getLogger(StudentProcessor.class);

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info(exchange.getIn().getBody());
		
		/*
		 * Map<String, Object> map=new HashMap<String, Object>(); map.put("processor1",
		 * new Integer(1));
		 * 
		 * Message msg=exchange.getIn(); msg.setBody(map, Map.class);
		 */
		
		exchange.setProperty("processor1", new Integer(1));
		StudentResponse response=new StudentResponse();
		
		Student student=new Student();
		student.setFirstName("Test");
		student.setLastName("Test");
		Student st=studentRepo.save(student);
		log.info("Saved Student:"+st.getId());
		exchange.setProperty("response",response);
		
	}

}
