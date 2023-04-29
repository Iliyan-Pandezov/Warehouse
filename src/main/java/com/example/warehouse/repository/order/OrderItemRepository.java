package com.example.warehouse.repository.order;

import com.example.warehouse.model.dao.order.OrderItemDAO;
import com.example.warehouse.model.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItemDAO> findAllByOrderId(Long id);
}
