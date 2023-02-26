package com.example.warehouse.web;

import com.example.warehouse.model.entity.User;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.CartService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;


@RestController
@RequestMapping("/users")
public class RestCartController {

    private final CartService cartService;

    private final AuthService authService;

    public RestCartController(CartService cartService, AuthService authService) {
        this.cartService = cartService;
        this.authService = authService;
    }

    @PostMapping("/cart/add/{id}/{quantity}")
    public String addProductToCart(@PathVariable("id") Long productId,
                                   @PathVariable("quantity") Integer quantity,
                                   Authentication authentication) {

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must log in in order to add this product to your cart!";
        }

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        Integer addedQuantity = cartService.addProduct(productId, quantity, currentUser);

        return quantity + " item(s) were added to your cart.";
    }

    @PostMapping("/cart/update/{id}/{quantity}")
    public String updateProductQuantityInCart(@PathVariable("id") Long productId,
                                              @PathVariable("quantity") Integer quantity,
                                              Authentication authentication) {

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must log in in order to update this product quantity in your cart!";
        }

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        BigDecimal subtotal = cartService.updateQuantity(quantity, productId, currentUser.getId());

        DecimalFormat df = new DecimalFormat("#,###.00");

        return df.format(subtotal);

    }

    @PostMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") Long productId,
                                        Authentication authentication) {

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must log in in order to remove this product from your cart!";
        }

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        cartService.removeProduct(productId, currentUser.getId());

        return "The Product has been removed successfully from your shopping cart!";

    }
}
