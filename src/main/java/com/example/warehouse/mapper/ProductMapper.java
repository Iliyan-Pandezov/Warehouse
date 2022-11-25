package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product DTOToProduct(ProductDTO productDTO);

//    CreateOrUpdateProductDTO

    ProductDTO ProductToDTO(Product product);
}
