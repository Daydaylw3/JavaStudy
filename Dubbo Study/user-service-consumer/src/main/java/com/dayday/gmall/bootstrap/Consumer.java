package com.dayday.gmall.bootstrap;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dayday.gmall.service.OrderService;

public class Consumer {
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
		OrderService orderService = context.getBean(OrderService.class);
		orderService.initOrder("1");
		System.out.println("end");
		System.in.read();
	}
	
}
