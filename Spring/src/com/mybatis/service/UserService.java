package com.mybatis.service;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.beans.UserBeans;
import com.mybatis.mapper.UserMapper;
import com.mybatis.tools.DBTools;

public class UserService {
	public static void main(String[] args) {
//		insertUser();		success
		updateUser();
//		deleteUser();		success
//		selectUserById();	success
//		selectAllUsers();	success
	}
	
	//
	private static void insertUser() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserBeans user = new UserBeans("dayday", "male", 10);
		try {
			mapper.insertUser(user);
			System.out.println(user);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
	
	//
	private static void updateUser() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		UserBeans user = new UserBeans("Faker", "male", 22);
		try {
			userMapper.updateUser(user, 1);
			System.out.println(user);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
	
	//
	private static void deleteUser() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			userMapper.deleteUser(2);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
	
	//
	private static void selectUserById() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			System.out.println(userMapper.selectUserById(3));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	private static void selectAllUsers() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			System.out.println(userMapper.selectAllUsers());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
