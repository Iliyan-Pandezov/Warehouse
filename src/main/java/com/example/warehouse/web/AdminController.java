package com.example.warehouse.web;

import com.example.warehouse.model.dto.CategoryDTO;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.Role;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.CategoryService;
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

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, CategoryService categoryService, ProductService productService, ProductRepository productRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/category")
    public String addCategory(@Valid CategoryDTO categoryDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryDTO", categoryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryDTO", bindingResult);

            return "redirect:/admin/category";
        }

        this.categoryService.addCategory(categoryDTO);

        return "redirect:/admin/category";
    }

    @GetMapping("/category")
    public String listOfCategories(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "categoryTest";

    }
    @GetMapping("/users")
    public String listOfUsers() {

        return "users";
    }

    @PostMapping("/category/update/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/products")
    public String listOfProducts() {
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@Valid ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request, @RequestParam("image") MultipartFile[] multipartFile) throws IOException {

//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("productDTO", productDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDTO", bindingResult);
//
//            return "redirect:/products";
//        }

        this.productService.addProduct(productDTO, multipartFile);

        return "redirect:/admin/products";
    }

    @PostMapping("/products/update/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.removeProduct(id);
        return "redirect:/admin/products";
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productRepository.findAll(Sort.by("addedOn"));
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.listAllCategories();
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @ModelAttribute("role")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


}
