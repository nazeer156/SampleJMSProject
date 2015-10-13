package com.indus.training.test;

import com.indus.training.domain.Person;
import com.indus.training.service.JMSReceiver;
import com.indus.training.service.JMSSender;

public class TestJMSObject {
   
	public static void main(String[] args) {
		   
		JMSSender jmsSender = new JMSSender();
		JMSReceiver jmsReceiver = new JMSReceiver();
		Person personObj = new Person();
		personObj.setPhoneNumber(90222);
		personObj.setName("prabanjan1");
		personObj.setAddress("greta nerw ln");
		jmsSender.sendMessage(personObj);
		jmsReceiver.receiveMessage();
		
	}
}
