package com.example.multicast.threads;

import java.util.TimerTask;

import org.springframework.stereotype.Component;

@Component
public class StudentThread extends TimerTask{

	private String name;

	public StudentThread(String name) {
	       this.name = name;
	   }
	@Override
	public void run() {
		System.out.println("Task "+name+"-executed...");
	}

}
