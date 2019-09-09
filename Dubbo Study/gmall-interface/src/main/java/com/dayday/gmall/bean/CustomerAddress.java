package com.dayday.gmall.bean;

import java.io.Serializable;

public class CustomerAddress implements Serializable {
	
	private static final long serialVersionUID = 1203389627441899333L;

	private Integer id;
	
	private String address;
	
	private String customerId;
	
	private String consignee;
	
	private String phoneNum;
	
	private boolean isDefault;
	
	public CustomerAddress(Integer id, 
							String address, 
							String customerId,
							String consignee,
							String phoneNum,
							boolean isDefault) {
		this.id = id;
		this.address = address;
		this.customerId = customerId;
		this.consignee = consignee;
		this.phoneNum = phoneNum;
		this.isDefault = isDefault;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("customerId = ").append(customerId);
		sb.append(", address = ").append(address);
		sb.append(", consignee = ").append(consignee);
		sb.append(", phoneNum = ").append(phoneNum);
		sb.append(", isDefault = ").append(isDefault);
		return sb.toString();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getConsignee() {
		return consignee;
	}
	
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
	
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
