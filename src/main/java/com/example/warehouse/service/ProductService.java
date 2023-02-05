package com.example.warehouse.service;

import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Image;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ImageRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final ImageRepository imageRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.imageRepository = imageRepository;
    }

//    public void addProduct(Old_ProductDTO productDTO, MultipartFile[] file) {
////        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
////
////        Optional<Image> image = imageRepository.findByName(fileName);
////
////        Product product = productMapper.DTOToProduct(productDTO);
////        if (image.isPresent()) {
////            product.setImage(image.get());
////        }
////        this.productRepository.save(product);
//    }

    public void removeProduct(Long id) {
        this.productRepository.deleteById(id);
    }

//    public void updateProduct(Product product, ProductDTO productDTO){
//
//    }

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