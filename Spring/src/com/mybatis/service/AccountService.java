package com.mybatis.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.beans.AccountBook;
import com.mybatis.mapper.AccountMapper;
import com.mybatis.tools.DBTools;

public class AccountService {
	
	private SqlSession session = DBTools.getSession();
	
	private AccountMapper accountMapper = session.getMapper(AccountMapper.class);
	
	public static void main(String[] args) {
//		String kind = "支出";
//		String category = "淘宝消费";
//		double amount = 59;
//		String description = "我和老婆的apple watch的表带";
//		AccountBook accountBook = new AccountBook(kind, category, amount, description);
//		System.out.println(accountBook);
		AccountService service = new AccountService();
//		System.out.println(service.insertRecord(accountBook));
		List<AccountBook> records = service.getRecordsByDate("2018-12-30");
		for (AccountBook record : records) {
			System.out.println(record);
		}
	}
	
	public int insertRecord(AccountBook accountBook) {
		try {
			return accountMapper.insertAccount(accountBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<AccountBook> getRecordsByDate(String date) {
		try {
			List<AccountBook> records = accountMapper.getRecordsByDate(date);
			return records;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
