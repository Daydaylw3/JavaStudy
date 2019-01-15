package com.mybatis.mapper;

import java.util.List;

import com.mybatis.beans.AccountBook;

public interface AccountMapper {
	/**
	 * 往账本表插入一条记录
	 * */
	public int insertAccount(AccountBook account) throws Exception;
	/**
	 * 根据日期返回账单记录
	 * */
	public List<AccountBook> getRecordsByDate(String date) throws Exception;
}
