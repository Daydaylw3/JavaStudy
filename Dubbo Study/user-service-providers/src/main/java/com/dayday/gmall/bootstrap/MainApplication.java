package com.dayday.gmall.bootstrap;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
		context.start();
		System.out.println("service started");
		System.in.read();
	}
	/**
	 * 医疗费		20w
	 * 公共交通	60w
	 * 飞机		200w
	 * 
	 * 95362 理赔电话
	 * 病历发票
	 * 
	 * 完美人生
	 * 身份证号码：
	 * 40579
	 * */
}
