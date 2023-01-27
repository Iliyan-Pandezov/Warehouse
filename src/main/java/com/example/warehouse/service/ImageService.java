package com.example.warehouse.service;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Image;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ImageRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public void addImage(MultipartFile file, ProductDTO productDTO) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Product product =  productRepository.findByName(productDTO.getName());


        Image image = new Image();

        image.setName(fileName);
        image.setFile(file.getBytes());
        image.setCreationDate(new Date());
        image.setProduct(product);
        this.imageRepository.save(image);
    }
}
