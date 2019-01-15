package com.mybatis.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 账本实体类
 * */
public class AccountBook implements Serializable {

	private static final long serialVersionUID = -8987613262807756084L;
	// id
	private int id;
	// 消费日期
	private String date;
	// 入账类型: 收入 or 支出
	private String kind;
	// 入账具体类型
	private String category;
	// 金额
	private double amount;
	// 具体描述
	private String description;
	
	/**
	 * 构造函数
	 * */
	public AccountBook() {
		
	}
	/**
	 * 构造函数
	 * 日期默认为当日
	 * 描述默认为空
	 * */
	public AccountBook(
			String kind,
			String category,
			double amount) {
		date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		if (!("支出".equals(kind) || "收入".equals(kind))) {
			throw new IllegalArgumentException("入账类型必须为收入或者支出");
		}
		this.kind = kind;
		this.category = category;
		this.amount = amount;
		description = "";
	}
	
	/**
	 * 构造函数
	 * 描述默认为空
	 * */
	public AccountBook(
			String date, 
			String kind, 
			String category, 
			double amount) {
		this(kind, category, amount);
		this.date = date;
	}
	
	/**
	 * 构造函数
	 * 日期默认为当日
	 * */
	public AccountBook(
			String kind, 
			String category, 
			double amount, 
			String description
			) {
		this(kind, category, amount);
		this.description = description;
	}
	
	/**
	 * 构造函数
	 * */
	public AccountBook(
			String date, 
			String kind, 
			String category, 
			double amount, 
			String description) {
		this(kind, category, amount, description);
		this.date = date;
	}
	
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 返回记录id
	 * @return id
	 * */
	public int getId() {
		return id;
	}

	/**
	 * 设置记录id
	 * @param id
	 * */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 返回日期
	 * @return date	日期
	 * */
	public String getDate() {
		return date;
	}

	/**
	 * 设置日期
	 * @param date	日期
	 * */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 返回入账类型
	 * @return kind	入账类型
	 * */
	public String getKind() {
		return kind;
	}

	/**
	 * 设置入账类型
	 * @param kind	入账类型
	 * */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 返回入账具体类型
	 * @return category	入账具体类型
	 * */
	public String getCategory() {
		return category;
	}

	/**
	 * 设置入账具体类型
	 * @param category	入账具体类型
	 * */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 返回金额
	 * @return amount	金额
	 * */
	public double getAmount() {
		return amount;
	}

	/**
	 * 设置金额
	 * @param amount		金额
	 * */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 返回具体描述
	 * @return description	具体描述
	 * */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置具体描述
	 * @param description	具体描述
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("date = " + date);
		sb.append("日, 由于" + category + kind + amount + ", 具体描述: ");
		sb.append("".equals(description) ? "无" : description);
		return sb.toString();
	}
}
