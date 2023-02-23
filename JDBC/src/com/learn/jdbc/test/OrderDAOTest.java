package com.learn.jdbc.test;

import com.learn.jdbc.dao.BookDAO;
import com.learn.jdbc.dao.OrderDAO;
import com.learn.jdbc.dao.OrderItemDAO;
import com.learn.jdbc.dao.UserDAO;
import com.learn.jdbc.dao.impl.BookDAOImpl;
import com.learn.jdbc.dao.impl.OrderDAOImpl;
import com.learn.jdbc.dao.impl.OrderItemDAOImpl;
import com.learn.jdbc.dao.impl.UserDAOImpl;
import com.learn.jdbc.entity.Book;
import com.learn.jdbc.entity.Order;
import com.learn.jdbc.entity.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderDAOTest {
    private UserDAO ud = new UserDAOImpl();
    private OrderDAO od = new OrderDAOImpl();
    private OrderItemDAO oid = new OrderItemDAOImpl();
    private BookDAO bd = new BookDAOImpl();
    private User login;
    private static Scanner input = new Scanner(System.in);

    /*
     * 测试登录
     */
    @Before
    public void test01() {
        System.out.println("----登录----");
        System.out.print("用户名：");
        String username = input.nextLine();

        System.out.print("密码：");
        String password = input.nextLine();

        User user = new User(username, password);
        login = ud.getUser(user);
        if (login == null) {
            System.out.println("登录失败，用户名或密码错误");
        } else {
            System.out.println("登录成功：" + login);
        }
    }

    /*
     * 测试查看某个用户查看自己的订单信息
     */
    @Test
    public void test02() {
        if (login == null) {
            System.out.println("请先登录");
            return;
        }

        List<Order> myOrders = od.getMyOrders(login.getId());
        for (Order order : myOrders) {
            System.out.println(order);
        }
    }

    /*
     * 模拟购物和结算
     */
    @Test
    public void test03() {
        if (login == null) {
            System.out.println("请先登录");
            return;
        }

        List<Book> books = bd.getBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        //因为没有更丰富的图形化界面支持，如果同一个本书买了两本，即输入两次相同id,当做两件商品来算
        System.out.print("请输入你要购买的图书的编号，用空格分割：");
        String str = input.nextLine();
        String[] ids = str.split(" ");

        //模拟购物车
        ArrayList<Book> list = new ArrayList<>();
        double totalAmount = 0;
        for (String bookId : ids) {
            Book book = bd.getBookById(bookId);
            list.add(book);
            totalAmount += book.getPrice();
        }

        //1、保存一个订单
        long time = System.currentTimeMillis();//获取当前系统时间距离1970-1-1 0:0:0 0毫秒的毫秒数
        String orderId = time + "" + login.getId();

        Order order = new Order(orderId, new Date(), list.size(), totalAmount, 0, login.getId());
        od.saveOrder(order);

        //2、保存所有的订单明细
        //3、更新该订单中的图书的销量和库存量
        //count,amount,title,author,price,img_path,order_id
        //sales = ? , stock = ? where id = ?";
        Object[][] arr1 = new Object[list.size()][7];
        Object[][] arr2 = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            arr1[i][0] = 1;
            arr1[i][1] = book.getPrice() * 1;
            arr1[i][2] = book.getTitle();
            arr1[i][3] = book.getAuthor();
            arr1[i][4] = book.getPrice();
            arr1[i][5] = book.getImgPath();
            arr1[i][6] = order.getId();

            arr2[i][0] = book.getSales() + 1;
            arr2[i][1] = book.getStock() - 1;
            arr2[i][2] = book.getId();
        }

        //批量添加订单明细
        oid.batchInsertOrderItems(arr1);

        //批量更新图书的销量和库存量
        bd.batchUpdateSalesAndStock(arr2);
    }

    @AfterClass
    public static void test04() {
        input.close();
    }
}