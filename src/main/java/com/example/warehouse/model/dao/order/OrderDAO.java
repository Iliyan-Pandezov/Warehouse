package com.example.warehouse.model.dao.order;

import com.example.warehouse.model.entity.User;
import com.example.warehouse.model.entity.order.OrderItem;
import com.example.warehouse.model.entity.order.ShippingAddress;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record OrderDAO (
        Long id,
        List<OrderItem> items,
        ShippingAddress address,
        User user,
        boolean isCompleted,
        Date submittedOn,
        LocalDateTime completedOn,
        BigDecimal total,
        boolean isCanceled
){
}
