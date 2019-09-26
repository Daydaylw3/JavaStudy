package com.dayday.gmall.service;

import java.util.List;

import com.dayday.gmall.bean.CustomerAddress;

public interface OrderService {
	
	/**
	 * 初始化订单
	 * @param customerId
	 */
	public List<CustomerAddress> initOrder(String customerId);
	
}
