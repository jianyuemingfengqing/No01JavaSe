package com.learn.jdbc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.learn.jdbc.tools.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


/**
 * 定义一个用来被继承的对数据库进行基本操作的Dao
 */
public abstract class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();
    // 定义一个变量来接收泛型的类型
    private Class<T> type;

    // 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
    public BasicDAO() {
        // 获取子类的类型
        Class clazz = this.getClass();
        // 获取父类的类型
        // getGenericSuperclass()用来获取当前类的父类的类型
        // ParameterizedType表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
        // 这个方法会返回一个Type的数组
        Type[] types = parameterizedType.getActualTypeArguments();
        // 获取具体的泛型的类型·
        this.type = (Class<T>) types[0];
    }

    /**
     * 通用的增删改操作
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCTools.getConnection();
        int count = 0;
        try {
            count = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * 获取一个对象
     *
     * @param sql
     * @param params
     * @return
     */
    public T getBean(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCTools.getConnection();
        T t = null;
        try {
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * 获取所有对象
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> getBeanList(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCTools.getConnection();
        List<T> list = null;
        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 获取一个单一值的方法，专门用来执行像select count(*)... 这样的sql语句
     *
     * @param sql
     * @param params
     * @return
     */
    public Object getSingleValue(String sql, Object... params) {
        // 获取连接
        Connection connection = JDBCTools.getConnection();
        Object count = null;
        try {
            count = queryRunner.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }
        return count;
    }
    /**
     * 进行批处理的方法
     * 关于二维数组Object[][] params
     * 		二维数组的第一维是sql语句要执行的次数
     * 		二维数组的第二维就是每条sql语句中要填充的占位符
     *
     * @param sql
     * @param params
     */
    public void batchUpdate(String sql , Object[][] params){
        //获取连接
        Connection connection = JDBCTools.getConnection();
        try {
            int[] batch = queryRunner.batch(connection ,sql, params);
        } catch (SQLException e) {
            //将编译时异常转换为运行时异常向上抛
            throw new RuntimeException(e);
        }
    }
}