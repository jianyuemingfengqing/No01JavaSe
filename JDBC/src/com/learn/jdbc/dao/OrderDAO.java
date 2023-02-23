package com.learn.jdbc.dao;


import com.learn.jdbc.entity.Order;

import java.util.List;

public interface OrderDAO {
	/**
	 * 保存订单的方法
	 * @param order Order 包含新订单的信息
	 */
	public void saveOrder(Order order);

	/**
	 * 获取我的订单的方法
	 * @param userId int 用户编号
	 * @return List<Order> 当前用户的所有订单
	 */
	public List<Order> getMyOrders(int userId);
}
