package com.learn.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private Integer sales;
    private Integer stock;
    private String imgPath;

    public Book(String title, String author, Double price, Integer sales, Integer stock, String imgPath) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

}
