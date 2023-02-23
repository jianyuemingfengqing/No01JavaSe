package com.learn.jdbc.test;

import com.learn.jdbc.dao.BookDAO;
import com.learn.jdbc.entity.Book;
import com.learn.jdbc.dao.impl.BookDAOImpl;
import com.learn.jdbc.tools.Page;
import org.junit.Test;


import java.util.List;
import java.util.Scanner;

public class BookDAOTest {
	private BookDAO bd = new BookDAOImpl();
	
	/*
	 * 测试添加图书
	 */
	@Test
	public void test01(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("请输入书名：");
		String title = input.nextLine();
		
		System.out.print("请输入作者：");
		String author = input.nextLine();
		
		System.out.print("请输入价格：");
		double price = input.nextDouble();
		
		System.out.print("请输入库存量：");
		int stock = input.nextInt();
		input.nextLine();
		
		System.out.print("请输入图片路径(例如：upload/books/从入门到精通.jpg：");
		String imgPath = input.nextLine();
		
		try {
			Book book = new Book(title, author, price, 0, stock, imgPath);
			bd.addBook(book);
			System.out.println("添加成功");
		} catch (Exception e) {
			System.out.println("添加失败，原因：" + e.getMessage());
		}
		
		input.close();
	}
	
	/*
	 * 测试修改图书
	 */
	@Test
	public void test02(){
		List<Book> books = bd.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
		
		Scanner input = new Scanner(System.in);
		System.out.print("请选择你要修改的图书的编号：");
		String bookId = input.nextLine();
		
		Book book = bd.getBookById(bookId);
		System.out.println("直接回车表示该项不修改");
		System.out.print("请输入书名("+book.getTitle()+")：");
		String title = input.nextLine();
		if (title.length() != 0){
			book.setTitle(title);
		}
		
		System.out.print("请输入作者("+book.getAuthor()+")：");
		String author = input.nextLine();
		if (author.length() != 0){
			book.setAuthor(author);
		}
		
		System.out.print("请输入价格("+book.getPrice()+")：");
		String strPrice = input.nextLine();
		if(strPrice.length() != 0){
			book.setPrice(Double.parseDouble(strPrice));
		}
		
		System.out.print("请输入库存量("+book.getStock()+")：");
		String stockStr = input.nextLine();
		if(stockStr.length() != 0){
			book.setStock(Integer.parseInt(stockStr));
		}
		
		System.out.print("请输入图片路径("+book.getImgPath()+")：");
		String imgPath = input.nextLine();
		if(imgPath.length() != 0){
			book.setImgPath(imgPath);
		}
		
		try {
			bd.updateBook(book);
			System.out.println("修改成功");
		} catch (Exception e) {
			System.out.println("修改失败，原因：" + e.getMessage());
		}
		input.close();
	}
	
	/*
	 * 测试删除图书
	 */
	@Test
	public void test03(){
		List<Book> books = bd.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
		
		Scanner input = new Scanner(System.in);
		System.out.print("请选择你要修改的图书的编号：");
		String bookId = input.nextLine();
		
		try {
			bd.deleteBookById(bookId);
			System.out.println("删除成功");
		} catch (Exception e) {
			System.out.println("删除失败，原因：" + e.getMessage());
		}
		input.close();
	}
	
	/*
	 * 测试图书查询，分页
	 */
	@Test
	public void test04(){
		Scanner input = new Scanner(System.in);
		
		Page<Book> page = new Page<>();
		page.setPageNo(1);
		
		boolean flag = true;
		while(flag){
			Page<Book> pageBooks = bd.getPageBooks(page);
			List<Book> list = pageBooks.getList();
			System.out.println("一共：" + page.getTotalPageNo() + "页，当前是第" + page.getPageNo() + "页");
			for (Book book : list) {
				System.out.println(book);
			}
			
			System.out.print("请选择页码：");
			int pageNo = input.nextInt();
			page = new Page<>();
			page.setPageNo(pageNo);
		}
		
		input.close();
	}
	
	/*
	 * 测试图书查询，分页，并加价格筛选
	 */
	@Test
	public void test05(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入价格范围：");
		System.out.print("最低价格：");
		double minPrice = input.nextDouble();
		
		System.out.print("最高价格：");
		double maxPrice = input.nextDouble();
		
		Page<Book> page = new Page<>();
		page.setPageNo(1);
		boolean flag = true;
		while(flag){
			Page<Book> pageBooks = bd.getPageBooksByPrice(page, minPrice, maxPrice);
			if(page.getTotalPageNo() == 0){
				System.out.println("该范围没有数据");
				break;
			}
			List<Book> list = pageBooks.getList();
			System.out.println("一共：" + page.getTotalPageNo() + "页，当前是第" + page.getPageNo() + "页");
			for (Book book : list) {
				System.out.println(book);
			}
			
			System.out.print("请选择页码：");
			int pageNo = input.nextInt();
			page = new Page<>();
			page.setPageNo(pageNo);
		}
		
		input.close();
	}
}