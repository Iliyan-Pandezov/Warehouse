package com.example.warehouse.web;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.CategoryService;
import com.example.warehouse.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String listOfProducts() {
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@Valid ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productDTO", productDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDTO", bindingResult);

            return "redirect:/products";
        }

        this.productService.addProduct(productDTO);

        return "redirect:/products";
    }

    @PostMapping("/products/update/delete/{id}")
    public String deleteProduct(@PathVariable("id") UUID id) {
        productService.removeProduct(id);
        return "redirect:/products";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.listAllCategories();
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productRepository.findAll(Sort.by("addedOn"));
    }

    @GetMapping("/customer/products")
    public String products() {
        return "test za produkti";
    }
}
