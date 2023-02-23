package com.learn.jdbc.dao.impl;


import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.OrderItemDAO;
import com.learn.jdbc.entity.OrderItem;

import java.util.List;

public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return null;
    }

    @Override
    public void batchInsertOrderItems(Object[][] params) {

    }
}
