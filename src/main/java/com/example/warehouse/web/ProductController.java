package com.example.warehouse.web;

import com.example.warehouse.mapper.ProductDTOMapper;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.model.entity.Image;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.CategoryService;
import com.example.warehouse.service.ImageService;
import com.example.warehouse.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final ProductDTOMapper productDTOMapper;

    public ProductController(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService, ImageService imageService, ProductDTOMapper productDTOMapper) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.productDTOMapper = productDTOMapper;
    }



    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.listAllCategories();
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productRepository.findAll(Sort.by("addedOn"));
    }

    @GetMapping("/products")
    public String products() {
        return "test za produkti";
    }

    @GetMapping("/products/{id}")
    public String singleProduct (Model model, @PathVariable("id") Long id){

        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isPresent()){

            ProductDTO currentProduct =productDTOMapper.apply((optionalProduct).get());
            List<Image> imageList = imageService.imageList(optionalProduct.get());
            model.addAttribute(currentProduct);
            model.addAttribute(imageList);
        } else {
            return "productNotFound";
        }


        return "product";
    }
}
