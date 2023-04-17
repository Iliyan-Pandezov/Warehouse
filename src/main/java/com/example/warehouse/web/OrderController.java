package com.example.warehouse.web;

import com.example.warehouse.model.dao.order.OrderDAO;
import com.example.warehouse.model.entity.*;
import com.example.warehouse.model.entity.order.Order;
import com.example.warehouse.repository.AddressRepository;
import com.example.warehouse.repository.CartRepository;
import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.ProfileRepository;
import com.example.warehouse.repository.order.OrderItemRepository;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.CartService;
import com.example.warehouse.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderService orderService, AuthService authService, CartService cartService, CartRepository cartRepository, AddressRepository addressRepository, ProfileRepository profileRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderService = orderService;
        this.authService = authService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.profileRepository = profileRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @GetMapping("/orders")
    public String orders(Authentication authentication, Model model) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Order> customersOrders = orderRepository.findAllByUser(currentUser);

        model.addAttribute("orders", customersOrders);

        return "pending_orders";
    }

    @PostMapping("/orders/submit")
    public String placeOrder(Authentication authentication, @RequestParam("a") String address) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Cart> cartItemsForOrder = cartRepository.findByUser(currentUser);

        if (cartItemsForOrder.isEmpty()) {
            return "redirect:/users/cart";
        }
        orderService.createOrder(currentUser, address, cartItemsForOrder);

        return "redirect:/users/orders";
    }

    @GetMapping("/orders/{id}")
    public String viewOrder(Model model,@PathVariable("id") Long orderId) {
        Order currentOrder = orderRepository.getOrderById(orderId);
        OrderDAO orderDAO = orderService.orderDAO(currentOrder);

        model.addAttribute("order",orderDAO);

        return "view_order";
    }
}
