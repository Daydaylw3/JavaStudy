package com.dayday.gmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dayday.gmall.bean.CustomerAddress;
import com.dayday.gmall.service.CustomerService;
import com.dayday.gmall.service.OrderService;

/**
 * @ClassName com.dayday.gmall.service.impl.OrderServiceImpl
 * @Description 
 * 
 * @author dayday
 * @date 2019年9月7日
 */
@Service
public class OrderServiceImpl implements OrderService {
	
//	@Autowired
	@Reference
	CustomerService customerService;
	
	@Override
	public List<CustomerAddress> initOrder(String customerId) {
		return customerService.getCustomerAddressList(customerId);
	}
	
}
