package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDetails(),
                product.getPrice(),
                product.getCategory(),
                product.getImageList()
        );
    }
}
