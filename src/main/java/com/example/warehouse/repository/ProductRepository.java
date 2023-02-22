package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

//    Product findById(Long id);

    @Transactional
    void deleteById(Long id);

    Product findByName(String name);

}
