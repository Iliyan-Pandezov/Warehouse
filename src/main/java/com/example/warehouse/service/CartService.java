package com.example.warehouse.service;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.CartRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
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

//    public Integer removeAProduct(Integer cartItemId, User user) {
//
//        int productsRemoved = 0;
//        List<Cart> cartItems = cartRepository.findByUser(user);
//
//        for (Cart cartItem : cartItems) {
//            if (cartItem.getId().equals(cartItemId)){
//                cartRepository.deleteById(cartItemId);
//                productsRemoved+=1;
//            }
//        }
//        return productsRemoved;
//    }

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

        BigDecimal subtotal = currentProduct.getPrice().multiply(BigDecimal.valueOf(quantity));

        return subtotal;
    }

    public void removeProduct(Long productId, Long userId) {
        cartRepository.deleteByProductAndCustomer(productId, userId);
    }
}
