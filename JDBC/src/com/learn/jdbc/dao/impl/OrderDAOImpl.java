package com.learn.jdbc.dao.impl;

import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.OrderDAO;
import com.learn.jdbc.entity.Order;

import java.util.List;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    @Override
    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?)";
        super.update(sql, order.getId(), order.getOrder_time(), order.getTotal_count(), order.getTotal_amount(), order.getState(), order.getUser_id());
    }

    @Override
    public List<Order> getMyOrders(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        return super.getBeanList(sql, userId);
    }
}
