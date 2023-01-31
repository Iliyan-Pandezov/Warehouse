package com.example.warehouse.service;

import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Image;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ImageRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    public void addProduct(ProductDTO productDTO, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Optional<Image> image = imageRepository.findByName(fileName);

        Product product = productMapper.DTOToProduct(productDTO);
        if (image.isPresent()) {
            product.setImage(image.get());
        }
        this.productRepository.save(product);
    }

    public void removeProduct(UUID id) {
        this.productRepository.deleteById(id);
    }

    //    public void updateProduct(Product product, ProductDTO productDTO){
//
//    }
}