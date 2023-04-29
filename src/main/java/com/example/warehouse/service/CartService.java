package com.example.warehouse.service;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.CartRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, AuthService authService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.authService = authService;
    }

    public List<Cart> listCartItems(User user) {
        return cartRepository.findByUser(user);
    }

    public Integer addProduct(Long productId, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        Cart cartItems = cartRepository.findByUserAndProduct(user, product);

        if (cartItems != null) {
            addedQuantity = cartItems.getQuantity() + quantity;
            cartItems.setQuantity(addedQuantity);
        } else {
            cartItems = new Cart();
            cartItems.setQuantity(quantity);
            cartItems.setUser(user);
            cartItems.setProduct(product);
        }
        cartRepository.save(cartItems);

        return addedQuantity;
    }

    public BigDecimal updateQuantity(Integer quantity, Long productId, Long userId) {

        cartRepository.updateQuantity(quantity, productId, userId);

        Product currentProduct = productRepository.findById(productId).get();

        return currentProduct.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void removeProduct(Long productId, Long userId) {
        cartRepository.deleteByProductAndCustomer(productId, userId);
    }

    public void clearCart(User user) {
        cartRepository.clearCartByUser(user.getId());
    }

    public int getItemCount(Authentication authentication) {
        User user = authService.getCurrentlyLoggedInCustomer(authentication);
        List<Cart> cart = cartRepository.findByUser(user);
        return cart.size();
    }
}
