package com.learn.jdbc.dao;

import com.learn.jdbc.entity.OrderItem;

import java.util.List;

public interface OrderItemDAO {
	/**
	 * 根据订单号获取对应的订单项
	 * 
	 * @param orderId String 订单编号
	 * @return List<OrderItem> 该订单的订单明细
	 */
	public List<OrderItem> getOrderItemsByOrderId(String orderId);

	/**
	 * 批量插入订单项的方法
	 * @param params Object[][] 行数就是有几项订单项，每一行就是一条订单明细记录
	 */
	public void batchInsertOrderItems(Object[][] params);
}
