package com.example.warehouse.service;

import com.example.warehouse.mapper.order.OrderDAOMapper;
import com.example.warehouse.model.dao.order.OrderDAO;
import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.order.Order;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.model.entity.order.OrderItem;
import com.example.warehouse.repository.AddressRepository;
import com.example.warehouse.repository.CartRepository;
import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.ProfileRepository;
import com.example.warehouse.repository.order.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProfileRepository profileRepository;
    private final AuthService authService;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;
    private final OrderDAOMapper orderDAOMapper;

    public OrderService(OrderRepository orderRepository, ProfileRepository profileRepository, AuthService authService, AddressRepository addressRepository, CartRepository cartRepository, CartService cartService, OrderItemRepository orderItemRepository, OrderDAOMapper orderDAOMapper) {
        this.orderRepository = orderRepository;
        this.profileRepository = profileRepository;
        this.authService = authService;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.orderItemRepository = orderItemRepository;
        this.orderDAOMapper = orderDAOMapper;
    }

    public void createOrder(User user, String addressId, List<Cart> cartItems) {

        Address shippingAddress = new Address();

        Optional<Address> selectedAddress = addressRepository.findById(Long.parseLong(addressId));

        if (selectedAddress.isPresent()) {
            shippingAddress = selectedAddress.get();
        }

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal total = new BigDecimal(0);

        Order order = new Order();

        for (Cart cartItem : cartItems) {
            OrderItem currentItem = new OrderItem();
            currentItem.setName(cartItem.getProduct().getName());
            currentItem.setQuantity(cartItem.getQuantity());
            currentItem.setPrice(cartItem.getProduct().getPrice());
            currentItem.setOrder(order);
            BigDecimal price = currentItem.getPrice().multiply(new BigDecimal(currentItem.getQuantity()));
            total = total.add(price);
            orderItems.add(currentItem);
        }

        order.setUser(user);
        order.setAddress(shippingAddress);
        order.setTotal(total);

        orderRepository.save(order);

        orderItemRepository.saveAll(orderItems);

        cartService.clearCart(user);
    }

    public OrderDAO orderDAO(Order order) {
        return orderDAOMapper.apply(order);
    }
}
