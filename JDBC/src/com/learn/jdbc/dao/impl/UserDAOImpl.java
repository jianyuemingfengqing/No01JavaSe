package com.learn.jdbc.dao.impl;

import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.UserDAO;
import com.learn.jdbc.entity.User;

public class UserDAOImpl extends BasicDAO<UserDAO> implements UserDAO {
    @Override
    public User getUser(User user) {
        return null;
    }

    @Override
    public boolean checkUserName(String username) {
        return false;
    }

    @Override
    public void saveUser(User user) {

    }
}
