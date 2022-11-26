package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.CategoryDTO;
import com.example.warehouse.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category DTOToCategory (CategoryDTO categoryDTO);

    CategoryDTO CategoryToDTO (Category category);
}
