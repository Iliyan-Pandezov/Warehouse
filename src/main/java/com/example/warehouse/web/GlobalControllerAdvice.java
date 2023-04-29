package com.example.warehouse.web;

import com.example.warehouse.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final CartService cartService;

    public GlobalControllerAdvice(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("itemCount")
    public Integer getItemCount(Authentication authentication){
        return cartService.getItemCount(authentication);
    }
}
