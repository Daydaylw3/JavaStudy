package com.dayday.gmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayday.gmall.bean.CustomerAddress;
import com.dayday.gmall.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/initOrder")
	public List<CustomerAddress> initOrder(@RequestParam("cid")String customerId) {
		return orderService.initOrder(customerId);
	}
	
}
