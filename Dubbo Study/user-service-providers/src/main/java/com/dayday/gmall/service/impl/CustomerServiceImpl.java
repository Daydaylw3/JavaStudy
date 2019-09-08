package com.dayday.gmall.service.impl;

import java.util.Arrays;
import java.util.List;

import com.dayday.gmall.bean.CustomerAddress;
import com.dayday.gmall.service.CustomerService;

/**
 * @ClassName com.dayday.gmall.service.impl.CustomerServiceImpl
 * @Description 
 * 
 * @author dayday
 * @date 2019年9月7日
 */
public class CustomerServiceImpl implements CustomerService {

	@Override
	public List<CustomerAddress> getCustomerAddressList(String customerId) {
		CustomerAddress ca1 = new CustomerAddress(1, "建工路5号", "1", "dayday", "13249360198", true);
		CustomerAddress ca2 = new CustomerAddress(2, "峰趣社区", "1", "dayday", "13249360198", false);
		return Arrays.asList(ca1, ca2);
	}
		
}
