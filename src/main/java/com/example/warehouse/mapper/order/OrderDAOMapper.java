package com.example.warehouse.mapper.order;

import com.example.warehouse.model.dao.order.OrderDAO;
import com.example.warehouse.model.entity.order.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDAOMapper implements Function<Order, OrderDAO> {
    @Override
    public OrderDAO apply(Order order) {
        return new OrderDAO(
                order.getId(),
                order.getItems(),
                order.getAddress(),
                order.getUser(),
                order.isCompleted(),
                order.getSubmittedOn(),
                order.getCompletedOn(),
                order.getTotal(),
                order.isCanceled()
        );
    }
}
