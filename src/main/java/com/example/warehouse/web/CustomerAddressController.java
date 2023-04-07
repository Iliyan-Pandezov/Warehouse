package com.example.warehouse.web;

import com.example.warehouse.model.dto.AddressDTO;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.service.AddressService;
import com.example.warehouse.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/profile")
public class CustomerAddressController {

    private final AuthService authService;
    private final AddressService addressService;

    public CustomerAddressController(AuthService authService, AddressService addressService) {
        this.authService = authService;
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public String createAddress(){
        return "address_create";
    }

    @ModelAttribute("addressDTO")
    public AddressDTO initAddress() {
        return new AddressDTO();
    }

    @PostMapping("/address/create")
    public String addressCreate(@Valid AddressDTO addressDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Authentication authentication) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addressDTO", addressDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressDTO", bindingResult);

            return "redirect:/users/profile/address/create";
        }

        this.addressService.createAddress(addressDTO, currentUser);

        return "redirect:/users/profile";
    }

}
