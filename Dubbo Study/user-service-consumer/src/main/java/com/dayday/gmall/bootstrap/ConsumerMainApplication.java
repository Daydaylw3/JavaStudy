package com.dayday.gmall.bootstrap;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dayday.gmall.service.IDemoService;
import com.dayday.gmall.service.OrderService;

public class ConsumerMainApplication {
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
		System.out.println("consumer started");
		System.in.read();
		OrderService orderService = context.getBean(OrderService.class);
		orderService.initOrder("1");
		IDemoService sayHello = (IDemoService) context.getBean("sayHello");
		System.out.println(sayHello.sayHello(""));
	}
	
}
