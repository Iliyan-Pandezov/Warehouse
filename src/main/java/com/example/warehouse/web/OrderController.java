package com.example.warehouse.web;

import com.example.warehouse.mapper.AddressDAOMapper;
import com.example.warehouse.mapper.order.ShippingAddressDAOMapper;
import com.example.warehouse.model.dao.order.OrderDAO;
import com.example.warehouse.model.dao.order.OrderItemDAO;
import com.example.warehouse.model.dao.order.ShippingAddressDAO;
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
import java.util.Objects;
import java.util.Optional;

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
    private final AddressDAOMapper addressDAOMapper;
    private final ShippingAddressDAOMapper shippingAddressDAOMapper;

    public OrderController(OrderService orderService, AuthService authService, CartService cartService, CartRepository cartRepository, AddressRepository addressRepository, ProfileRepository profileRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, AddressDAOMapper addressDAOMapper, ShippingAddressDAOMapper shippingAddressDAOMapper) {
        this.orderService = orderService;
        this.authService = authService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.profileRepository = profileRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.addressDAOMapper = addressDAOMapper;
        this.shippingAddressDAOMapper = shippingAddressDAOMapper;
    }


    @GetMapping("/orders/pending")
    public String pendingOrders(Authentication authentication, Model model) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Order> pendingOrders = orderRepository.findAllByUserAndIsCompleted(currentUser, false);

        model.addAttribute("orders", pendingOrders);

        return "pending_orders";
    }

    @GetMapping("/orders/completed")
    public String completedOrders(Authentication authentication, Model model) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Order> completedOrders = orderRepository.findAllByUserAndIsCompleted(currentUser, true);

        model.addAttribute("orders", completedOrders);

        return "completed_orders";
    }

    @PostMapping("/orders/submit")
    public String placeOrder(Authentication authentication, @RequestParam("a") String address) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Cart> cartItemsForOrder = cartRepository.findByUser(currentUser);

        if (cartItemsForOrder.isEmpty()) {
            return "redirect:/users/cart";
        }
        orderService.createOrder(currentUser, address, cartItemsForOrder);

        return "redirect:/users/orders/pending";
    }

    @GetMapping("/orders/{id}")
    public String viewOrder(Authentication authentication, Model model, @PathVariable("id") Long orderId) {
        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
        Optional<Order> optionalOrder = orderRepository.findOrderById(orderId);
        Order currentOrder = new Order();
        if (optionalOrder.isEmpty()) {
            return "redirect:/";
        } else {
            currentOrder = optionalOrder.get();
        }
        if (!Objects.equals(currentOrder.getUser(), currentUser)) {
            return "redirect:/";
        }
        OrderDAO orderDAO = orderService.orderDAO(currentOrder);

        List<OrderItemDAO> orderItemList = orderItemRepository.findAllByOrderId(currentOrder.getId());

        ShippingAddressDAO address = shippingAddressDAOMapper.apply(currentOrder.getAddress());


        model.addAttribute("order", orderDAO);
        model.addAttribute("address", address);
        model.addAttribute("ordersItems", orderItemList);

        return "view_order";
    }
}
