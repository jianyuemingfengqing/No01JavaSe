package com.learn.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private LocalDateTime order_time;
    private Integer total_count;
    private Double total_amount;
    private Integer state;
    private Integer user_id;
}
