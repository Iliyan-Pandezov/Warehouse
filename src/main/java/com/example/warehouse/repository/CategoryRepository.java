package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Transactional
    void deleteById(Long id);

    Optional<Category> findByName(String name);
}
