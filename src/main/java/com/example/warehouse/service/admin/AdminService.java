package com.example.warehouse.service.admin;

import com.example.warehouse.mapper.order.OrderDAOMapper;
import com.example.warehouse.model.dao.order.OrderDAO;
import com.example.warehouse.model.dao.order.OrderItemDAO;
import com.example.warehouse.model.entity.order.Order;
import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.order.OrderItemRepository;
import com.example.warehouse.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderDAOMapper orderDAOMapper;
    private final OrderItemRepository orderItemRepository;

    public AdminService(AuthService authService, OrderRepository orderRepository, OrderDAOMapper orderDAOMapper, OrderItemRepository orderItemRepository) {
        this.authService = authService;
        this.orderRepository = orderRepository;
        this.orderDAOMapper = orderDAOMapper;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDAO> listOfOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders
                .stream()
                .map(this::mapOrderToOrderDAO)
                .collect(Collectors.toList());
    }
    public List<OrderDAO> listOfOrdersPending() {
        List<Order> allOrders = orderRepository.findAllByIsCompletedFalse();
        return allOrders
                .stream()
                .map(this::mapOrderToOrderDAO)
                .collect(Collectors.toList());
    }
    public List<OrderDAO> listOfOrdersCompleted() {
        List<Order> allOrders = orderRepository.findAllByIsCompletedTrue();
        return allOrders
                .stream()
                .map(this::mapOrderToOrderDAO)
                .collect(Collectors.toList());
    }

    private OrderDAO mapOrderToOrderDAO(Order order) {
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

    public OrderDAO order(Long id) {

        Optional<Order> currentOrder = orderRepository.findById(id);

//        if (currentOrder.isEmpty()){
//            return ;
//        }
        return orderDAOMapper.apply(currentOrder.get());

    }

    public List<OrderItemDAO> orderItemList(OrderDAO order){
        return orderItemRepository.findAllByOrderId(order.id());
    }

    public void confirmOrder(Long id){
        Order currentOrder = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + id));
        currentOrder.setCompleted(true);
        currentOrder.setCompletedOn(LocalDateTime.now());
        orderRepository.save(currentOrder);
    }
}
