package com.example.warehouse.web;

import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String listOfProducts(Model model) {
        List<Product> listProducts = productRepository.findAll();
        model.addAttribute("products", listProducts);
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

    @PostMapping("/product/update/delete/{id}")
    public String deleteProduct(@PathVariable("id") UUID id) {
        productService.removeProduct(id);
        return "redirect:/products";
    }
}
