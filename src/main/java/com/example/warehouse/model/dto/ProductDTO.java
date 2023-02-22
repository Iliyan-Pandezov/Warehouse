package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Category;
import com.example.warehouse.model.entity.Image;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(
        Long id,
        String name,
        String details,
        BigDecimal price,
        Category category,
        List<Image> images
) {
}
