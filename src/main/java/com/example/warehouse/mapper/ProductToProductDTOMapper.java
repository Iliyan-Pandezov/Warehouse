package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductToProductDTOMapper implements Function<ProductDTO, Product> {
    @Override
    public Product apply(ProductDTO productDTO) {
        return new Product(
        );
    }
}
