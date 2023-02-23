package com.learn.jdbc.test;

import com.learn.jdbc.dao.UserDAO;
import com.learn.jdbc.dao.impl.UserDAOImpl;
import com.learn.jdbc.entity.User;
import org.junit.Test;

import java.util.Scanner;

public class UserDAOTest {
	private UserDAO ud = new UserDAOImpl();
	
	/*
	 * 登录测试
	 */
	@Test
	public void test01(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("----登录----");
		System.out.print("用户名：");
		String username = input.nextLine();
		
		System.out.print("密码：");
		String password = input.nextLine();
		
		User user = new User(username,password);
		user = ud.getUser(user);
		if(user == null){
			System.out.println("登录失败，用户名或密码错误");
		}else{
			System.out.println("登录成功：" + user);
		}
		
		input.close();
	}
	
	/*
	 * 注册测试
	 */
	@Test
	public void test02(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("----注册----");
		String username;
		while(true){
			System.out.print("用户名：");
			username = input.nextLine();
			
			if(ud.checkUserName(username)==false){
				break;
			}else{
				System.out.println("用户名已存在，请重写输入");
			}
		}
		
		System.out.print("密码：");
		String password = input.nextLine();
		
		System.out.print("邮箱：");
		String email = input.nextLine();
		
		try {
			User user = new User(username,password,email);
			ud.saveUser(user);
			System.out.println("注册成功");
		} catch (Exception e) {
			System.out.println("注册失败，原因：" + e.getMessage());
		}
		
		input.close();
	}
}