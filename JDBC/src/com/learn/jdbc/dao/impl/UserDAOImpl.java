package com.learn.jdbc.dao.impl;

import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.UserDAO;
import com.learn.jdbc.entity.User;

public class UserDAOImpl extends BasicDAO<User> implements UserDAO {
    @Override
    public User getUser(User user) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = sha1(?)";
        return super.getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean checkUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return super.getBean(sql, username) != null;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users VALUES (0,?,sha1(?),?)";
        super.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
