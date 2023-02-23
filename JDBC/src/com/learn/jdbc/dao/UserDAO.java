package com.learn.jdbc.dao;

import com.learn.jdbc.entity.User;

public interface UserDAO {
	/**
	 * 根据用户名和密码获取数据库中的记录
	 * @param user User 该用户对象中保存了登录时填写的用户名和密码
	 * @return User：如果用户名和密码正确返回User对象的完整信息，如果用户名或密码不正确，则返回null
	 */
	public User getUser(User user);

	/**
	 * 根据用户名获取数据库中的记录
	 * @param username String 该用户对象中保存了注册时新添加的用户名
	 * @return true：用户名已存在， false：用户名不存在，说明该可用
	 */
	public boolean checkUserName(String username);

	/**
	 * 将新用户保存到数据库
	 * @param user User 该用户对象中保存了注册时新添加的用户所有信息
	 */
	public void saveUser(User user);
}