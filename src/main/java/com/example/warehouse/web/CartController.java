package com.example.warehouse.web;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class CartController {

    private final CartService cartService;

    private final AuthService authService;

    private final UserRepository userRepository;

    public CartController(CartService cartService, AuthService authService, UserRepository userRepository) {
        this.cartService = cartService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/cart")
    public String listOfProducts(Model model, Authentication authentication) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        List<Cart> cartItems = cartService.listCartItems(currentUser);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "cart";
    }

    @PostMapping("/cart/delete/{id}")
    public String removeAProduct(@PathVariable("id") Integer cartItemId,
                                 Authentication authentication) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
        cartService.removeAProduct(cartItemId, currentUser);

        return "redirect:/users/cart";
    }

    @PostMapping("/cart/add/{quantity}/{id}")
    public String addProductToCart(@PathVariable("id") UUID productId,
                                   @PathVariable("quantity") Integer quantity,
                                   Authentication authentication) {
        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
        cartService.addProduct(productId, quantity, currentUser);

        return "redirect:/customer/products";
    }
}
