package com.example.warehouse.service;

import com.example.warehouse.model.entity.Image;
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
import java.util.Date;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

//    private final Path ProductImages = Paths.get("/images/ProductImages/");

    private final Path ProductImages = Paths.get("./src/main/resources/static/images/ProductImages/");

    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public void addImage(MultipartFile[] file) throws IOException {
        for (MultipartFile multipartFile : file) {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            if (fileName.equals(imageRepository.findByName(fileName))) {
                return;
            }
            Image image = new Image();

            Path imagePath = Paths.get(ProductImages + "/" + fileName);

            image.setName(fileName);
            Files.copy(multipartFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            image.setUrl(imagePath.toString());
            image.setCreationDate(new Date());
            this.imageRepository.save(image);
        }
//        Product product = productRepository.findByName(productDTO.getName());


    }

    //TODO to find a way to delete images from local directory automatically with the product
}
