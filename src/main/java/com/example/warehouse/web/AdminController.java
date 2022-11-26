package com.example.warehouse.web;

import com.example.warehouse.model.dto.CategoryDTO;
import com.example.warehouse.model.entity.Category;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public AdminController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
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
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("categories", listCategories);
        return "categoryTest";
    }

    @PostMapping("/category/update/delete/{id}")
    public String deleteCategory(@PathVariable("id") UUID id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/category";
    }
}
