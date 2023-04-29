package com.example.warehouse.repository;

import com.example.warehouse.model.entity.order.Order;
import com.example.warehouse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserAndIsCompleted(User currentUser, boolean isCompleted);

    Optional<Order> findOrderById(Long id);
    Order getOrderById(Long orderId);

    List<Order> findAllByIsCompletedFalse();
    List<Order> findAllByIsCompletedTrue();
}
