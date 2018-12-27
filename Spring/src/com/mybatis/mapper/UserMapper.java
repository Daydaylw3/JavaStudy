package com.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mybatis.beans.UserBeans;

public interface UserMapper {
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 * */
	public int insertUser(UserBeans user) throws Exception;
	/**
	 * 修改用户
	 * @param user
	 * @param id
	 * @return
	 * @throws Exception
	 * */
	public int updateUser(@Param("user_id")int id, @Param("user")UserBeans user) throws Exception;
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws Exception
	 * */
	public int deleteUser(int id) throws Exception;
	/**
	 * 根据id查询用户
	 * @param id
	 * @return UserBeans
	 * @throws Exception
	 * */
	public UserBeans selectUserById(int id) throws Exception;
	/**
	 * 查询所有用户信息
	 * @return List<UserBeans>
	 * @throws Excepiton
	 * */
	public List<UserBeans> selectAllUsers() throws Exception;
	/**
	 * 查询所有用户的用户名
	 * @return List<String>
	 * @throws Exception
	 * */
	public List<String> selectAllUserName() throws Exception;
	/**
	 * 查询所有用户用户名以及年龄
	 * */
	public List<Map<String, String>> selectAllUserIdAndAge() throws Exception;
}
