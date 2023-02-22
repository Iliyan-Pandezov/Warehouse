package com.example.warehouse.web;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        //SecurityContextHolder.getContext().getAuthentication();

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

    @PostMapping("/cart/add/{id}/{quantity}")
    public String addProductToCart(@PathVariable("id") Long productId,
//                                   @RequestParam("quantity") String quantity,
                                   @PathVariable("quantity") Integer quantity,
                                   Authentication authentication) {
        System.out.println(productId + quantity);
        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
        cartService.addProduct(productId, quantity, currentUser);

        return "redirect:/customer/products";
    }
}
