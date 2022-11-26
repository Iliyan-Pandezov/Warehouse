package com.example.warehouse.service;

import com.example.warehouse.mapper.CategoryMapper;
import com.example.warehouse.model.dto.CategoryDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public void addCategory(CategoryDTO categoryDTO) {
        Optional<Category> byName = this.categoryRepository.findByName(categoryDTO.getName());
        if (byName.isPresent()) {
            throw new RuntimeException("category.present");
        }
        Category newCategory = this.categoryMapper.DTOToCategory(categoryDTO);
        this.categoryRepository.save(newCategory);
    }

    public void removeCategory(UUID id) {
        this.categoryRepository.deleteById(id);
    }
}
