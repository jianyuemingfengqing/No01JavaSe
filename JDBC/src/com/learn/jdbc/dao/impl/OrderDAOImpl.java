package com.learn.jdbc.dao.impl;


import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.OrderDAO;
import com.learn.jdbc.entity.Order;

import java.util.List;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public List<Order> getMyOrders(int userId) {
        return null;
    }
}
