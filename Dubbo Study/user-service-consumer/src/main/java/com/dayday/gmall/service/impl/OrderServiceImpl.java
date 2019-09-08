package com.dayday.gmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public void initOrder(String customerId) {
		// 1. 查询用户的收货地址
		List<CustomerAddress> addressList = customerService.getCustomerAddressList(customerId);
		for (CustomerAddress a : addressList) 
			System.out.println(a);
	}
	
}
