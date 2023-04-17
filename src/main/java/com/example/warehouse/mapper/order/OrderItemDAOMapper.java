package com.example.warehouse.mapper.order;

import com.example.warehouse.model.dao.order.OrderItemDAO;
import com.example.warehouse.model.entity.order.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDAOMapper implements Function<OrderItem, OrderItemDAO> {
    @Override
    public OrderItemDAO apply(OrderItem orderItem) {
        return new OrderItemDAO(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getOrder()
        );
    }
}
