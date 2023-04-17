package com.example.warehouse.model.dao.order;

import com.example.warehouse.model.entity.order.Order;

import java.math.BigDecimal;

public record OrderItemDAO (
        Long id,
        String name,
        int quantity,
        BigDecimal price,
        Order order
){
}
