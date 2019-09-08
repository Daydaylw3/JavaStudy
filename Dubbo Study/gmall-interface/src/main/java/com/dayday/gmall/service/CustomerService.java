package com.dayday.gmall.service;

import java.util.List;

import com.dayday.gmall.bean.CustomerAddress;

/**
 * @ClassName com.dayday.gmall.service.CustomerService
 * @Description 
 * 
 * @author dayday
 * @date 2019年9月7日
 */
public interface CustomerService {
	
	/**
	 * 根据顾客id返回所有的收货地址
	 * */
	public List<CustomerAddress> getCustomerAddressList(String customerId);
	
}
