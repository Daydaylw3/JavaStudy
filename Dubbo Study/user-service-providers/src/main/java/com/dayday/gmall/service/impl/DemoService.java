package com.dayday.gmall.service.impl;

import java.util.concurrent.TimeUnit;

import com.dayday.gmall.service.IDemoService;

/**
 * @ClassName com.dayday.gmall.service.impl.DemoService
 * 
 * @author dayday
 * @date 2019年9月22日
 */
public class DemoService implements IDemoService {
	
	@Override
	public String sayHello(String name) {
		System.out.println("...try...");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello, " + name;
	}
	
}
