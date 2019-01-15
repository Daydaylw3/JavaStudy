package com.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.beans.UserBeans;
import com.mybatis.mapper.UserMapper;
import com.mybatis.tools.DBTools;

public class UserService {
	public static void main(String[] args) {
//		insertUser();			//success
//		updateUser();			//success
//		deleteUser();			//success
//		selectUserById();		//success
//		selectAllUsers();		//success
//		selectAllUserName();		//success
//		selectAllUserNameAndAge();
		selectUsersWithFuzzyName();
	}
	
	//
	private static void insertUser() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserBeans user = new UserBeans("Visan", "female", 18);
		try {
			mapper.insertUser(user);
			System.out.println(user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
	
	//
	private static void updateUser() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		UserBeans user = new UserBeans("Faker", "male", 20);
		try {
			userMapper.updateUser(1, user);
			System.out.println(user);
			session.commit();
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	private static void selectAllUsers() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			System.out.println(userMapper.selectAllUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void selectAllUserName() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			System.out.println(userMapper.selectAllUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void selectAllUserNameAndAge() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			// 看上去底层mybatis会返回一个ArrayList
//			LinkedList<UserBeans> result = (LinkedList<UserBeans>) userMapper.selectAllUserNameAndAge();
			ArrayList<UserBeans> result = (ArrayList<UserBeans>) userMapper.selectAllUserNameAndAge();
			for (UserBeans user : result) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void selectUsersWithFuzzyName() {
		SqlSession session = DBTools.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			List<UserBeans> result = userMapper.selectUsersWithFuzzyName("isa");
			for (UserBeans user : result) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
