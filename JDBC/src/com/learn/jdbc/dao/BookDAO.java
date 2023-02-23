package com.learn.jdbc.dao;

import com.learn.jdbc.entity.Book;
import com.learn.jdbc.tools.Page;

import java.util.List;

public interface BookDAO {

	/**
	 * 添加图书的方法
	 * @param book Book 一本新添加的图书对象
	 */
	public void addBook(Book book);

	/**
	 * 根据图书的id删除图书的方法
	 * @param bookId String 图书编号，这里用String类型是因为从Web客户端传过来的数据都是String类型的
	 */
	public void deleteBookById(String bookId);

	/**
	 * 根据图书的id获取图书信息
	 * @param bookId String 图书编号，这里用String类型是因为从Web客户端传过来的数据都是String类型的
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 更新图书信息的方法
	 * @param book Book 该对象中包含了一本图书的完整信息，其中部分属性可能是新修改的，部分属性是保留原来的
	 */
	public void updateBook(Book book);
	
	/**
	 * 获取所有图书的方法
	 * 
	 * @return List<Book> 所有图书
	 */
	public List<Book> getBooks();

	/**
	 * 获取带分页的所有图书信息
	 * @param page Page<Book> 传入是一个带页码（即你要查询第几页），但不包含Book对象的page对象
	 * @return 返回的page对象是包含所有属性的page对象
	 */
	public Page<Book> getPageBooks(Page<Book> page);

	/**
	 * 根据价格范围获取带分页的图书信息
	 * 
	 * @param page Page<Book> 传入是一个带页码（即你要查询第几页），但不包含Book对象的page对象
	 * @param minPrice double 价格区间范围的左边界
	 * @param maxPrice double 价格区间范围的右边界
	 * @return Page<Book> 返回的page对象是包含所有属性的page对象
	 */
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);

	/**
	 * 批量更新图书的库存和销量
	 * @param params Object[][] 其中行数是代表修改几本数，每一个行包含三个数据，bookid，库存量，销量
	 */
	public void batchUpdateSalesAndStock(Object[][] params);
}