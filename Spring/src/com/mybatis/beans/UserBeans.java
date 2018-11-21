package com.mybatis.beans;

import java.io.Serializable;

public class UserBeans implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//id 自增长
	private Integer id;
	//姓名 不可为空
	private String name;
	//性别 默认为男
	private String sex = "男";
	//年龄 不可为空
	private int age;
	
	public UserBeans() {
		super();
	}
	public UserBeans(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "UserBeans [ id = " + id + ", name = " + name + ", sex = "
				+ sex + ", age = " + age + " ]";
	}
}
