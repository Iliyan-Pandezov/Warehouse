package com.example.warehouse.service;

import com.example.warehouse.model.dto.CategoryDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(CategoryDTO categoryDTO) {
        Optional<Category> byName = this.categoryRepository.findByName(categoryDTO.name());
        if (byName.isPresent()) {
            throw new RuntimeException("category.present");
        }

        Category newCategory = new Category();
        newCategory.setName(categoryDTO.name());

        this.categoryRepository.save(newCategory);
    }

    public void removeCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public List<Category> listAllCategories() {
        return this.categoryRepository.findAll();
    }

}
