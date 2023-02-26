package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

    @Transactional
    void deleteById(Integer id);

    @Query(value = "UPDATE Carts c SET c.quantity = ?1 WHERE c.product_id = ?2 AND c.customer_id = ?3", nativeQuery = true)
    @Modifying
    void updateQuantity(Integer quantity, Long productId, Long userId);

    @Query(value = "DELETE FROM Carts c WHERE c.product_id = ?1 AND c.customer_id = ?2", nativeQuery = true)
    @Modifying
    void deleteByProductAndCustomer(Long productId, Long userId);
}
