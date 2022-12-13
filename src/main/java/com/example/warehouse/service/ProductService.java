package com.example.warehouse.service;

import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.DTOToProduct(productDTO);
        this.productRepository.save(product);
    }

    public void removeProduct(UUID id) {
        this.productRepository.deleteById(id);
    }

    //    public void updateProduct(Product product, ProductDTO productDTO){
//
//    }
}