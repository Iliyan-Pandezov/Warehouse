package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

    @Transactional
    void deleteById(Integer id);
}
