package com.learn.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer id;
    private Integer count;
    private Double amount;
    private String title;
    private String author;
    private Double price;
    private String img_path;
    private String order_id;
}
