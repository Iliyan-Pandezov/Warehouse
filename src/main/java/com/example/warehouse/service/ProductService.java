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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    public void removeProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public void addProduct(ProductDTO productDTO, MultipartFile[] fileList) throws IOException {
        final Path ProductImages = Paths.get("./src/main/resources/static/images/ProductImages/");

        List<Image> imageList = new ArrayList<>();

        if (fileList.length > 0) {
            for (MultipartFile file : fileList) {

                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                Image image = new Image();

                image.setName(fileName);
                image.setCreationDate(new Date());

                imageList.add(image);

                Path imagePath = Paths.get(ProductImages + "/" + fileName);
                Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                this.imageRepository.save(image);
            }
        }

        Product product = new Product();

        product.setName(productDTO.name());
        product.setDetails(productDTO.details());
        product.setPrice(productDTO.price());
        product.setCategory(productDTO.category());
        product.setImageList(imageList);

        this.productRepository.save(product);
    }

}