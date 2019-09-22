package com.dayday.gmall.service.stub;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dayday.gmall.service.IDemoService;

/**
 * 本地存根代码
 * 
 * @ClassName com.dayday.gmall.service.stub.DemoStub
 * 
 * @author dayday
 * @date 2019年9月22日
 */
public class DemoStub implements IDemoService {
	private final IDemoService service;
	
	public DemoStub(IDemoService service) {
		super();
		this.service = service;
	}
	
	@Override
	public String sayHello(String name) {
		if (StringUtils.isBlank(name))
			return "what's your name?";
		return service.sayHello(name);
	}

}
