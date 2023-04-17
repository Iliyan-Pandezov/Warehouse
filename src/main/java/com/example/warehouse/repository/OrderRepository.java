package com.example.warehouse.repository;

import com.example.warehouse.model.entity.order.Order;
import com.example.warehouse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User currentUser);

    Order getOrderById(Long orderId);

}
