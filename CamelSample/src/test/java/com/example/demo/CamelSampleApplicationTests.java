package com.example.demo;

import com.example.persitence.model.Student;
import com.example.persitence.repo.StudentRepo;
import com.example.processor.StudentProcessor;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.AssertionErrors;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(CamelSpringBootRunner.class)
class CamelSampleApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private StudentProcessor studentProcessor;

	@MockBean
	private StudentRepo studentRepo;

	@Test
	public void getAllStudents(){
		Mockito.when(studentRepo.findAll()).thenReturn(Stream.of(
				new Student(1L,"Abhijit","Dey"),
				new Student(2L,"Santunu","Dey")
		).collect(Collectors.toList()));

		AssertionErrors.assertEquals("Comparing list sizes:",2L,studentRepo.findAll().spliterator().getExactSizeIfKnown());
	}

	@Test
	public void delUser(){
		Student student=new Student(1L,"Abhijit","Dey");
		studentRepo.delete(student);
		Mockito.verify(studentRepo,Mockito.times(1)).delete(student);
	}
}
