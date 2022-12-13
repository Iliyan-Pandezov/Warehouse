package com.example.warehouse.service;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.CartRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> listCartItems(User user) {
        return cartRepository.findByUser(user);
    }

    public Integer addProduct(UUID productId, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        Cart cartItem = cartRepository.findByUserAndProduct(user, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new Cart();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }
        cartRepository.save(cartItem);
        return addedQuantity;
    }

    public void addAProduct(UUID productId, Integer quantity, User user) {
        Integer quantityToBeAdded = quantity;

        Product product = productRepository.findById(productId).get();

        Cart cartItems = cartRepository.findByUserAndProduct(user, product);

        if (cartItems != null) {
            quantityToBeAdded = cartItems.getQuantity() + quantity;
            cartItems.setQuantity(quantityToBeAdded);
            cartRepository.save(cartItems);
        } else {
            cartItems = new Cart();
            cartItems.setQuantity(quantity);
            cartItems.setUser(user);
            cartItems.setProduct(product);

            cartRepository.save(cartItems);
        }
    }
}
