package com.example.warehouse.web;

import com.example.warehouse.model.dto.UserRegistrationDTO;
import com.example.warehouse.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/users/newRegister";
        }

        this.authService.register(userRegistrationDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
