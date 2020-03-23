package com.example.multicast.threads;

import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StudentThread extends TimerTask{

	private static final Logger log=LogManager.getLogger(StudentThread.class);
	
	private String name;

	public StudentThread(String name) {
	       this.name = name;
	   }
	@Override
	public void run() {
		log.info("Task "+name+"-executed...");
	}

}
