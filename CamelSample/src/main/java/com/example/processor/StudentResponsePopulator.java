package com.example.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.borokali.model.res.StudentResponse;

@Component
public class StudentResponsePopulator implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("Inside StudentResponsePopulator");
		
		Map<String, Object> map=exchange.getIn().getBody(Map.class);
		
		Integer id1 = (Integer)map.get("processor1");
		Integer id2 = (Integer)map.get("processor2");
		
		Integer idTot=id1+id2;
		
		StudentResponse response=new StudentResponse();
		response.setRollNo(idTot);
		response.setAddr("TestAddr");
		
		System.out.println("Setting response...");
		Message msg=exchange.getOut();
		msg.setBody(response, StudentResponse.class);
	}

}
