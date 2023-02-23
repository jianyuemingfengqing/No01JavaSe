package com.learn.jdbc.dao.impl;


import com.learn.jdbc.dao.BasicDAO;
import com.learn.jdbc.dao.BookDAO;
import com.learn.jdbc.entity.Book;
import com.learn.jdbc.tools.Page;

import java.util.List;

public class BookDAOImpl extends BasicDAO<Book> implements BookDAO {

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books VALUES (0,?, ?, ?, ?, ?, ?)";
        super.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public void deleteBookById(String bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        super.update(sql, bookId);
    }

    @Override
    public Book getBookById(String bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return super.getBean(sql, bookId);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?,author=?,price=?,stock=?,img_path=? WHERE id=?";
        super.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public List<Book> getBooks() {
        String sql = "SELECT * FROM books";
        return super.getBeanList(sql);
    }

    @Override
    public Page<Book> getPageBooks(Page<Book> page) {
        page.setTotalRecord(getBooks().size());
        String sql = "SELECT * FROM books LIMIT ?,?";
        int pageNo = page.getPageNo();
        List<Book> bookList = super.getBeanList(sql, Page.PAGE_SIZE * (pageNo - 1), Page.PAGE_SIZE);

        page.setList(bookList);
        return page;
    }

    @Override
    public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice) {
        page.setTotalRecord(getBooks().size());
        int pageNo = page.getPageNo();
        String sql = "SELECT * FROM books WHERE price>=? AND price<=?  LIMIT ?,?";
        List<Book> bookList = super.getBeanList(sql, minPrice, maxPrice,Page.PAGE_SIZE * (pageNo - 1), Page.PAGE_SIZE);
        page.setList(bookList);
        return page;
    }

    @Override
    public void batchUpdateSalesAndStock(Object[][] params) {

    }
}