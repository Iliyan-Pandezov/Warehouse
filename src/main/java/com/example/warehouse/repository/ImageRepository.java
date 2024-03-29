package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository  extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String fileName);

//    List<Image> findAllByProductId(Long id);
}
