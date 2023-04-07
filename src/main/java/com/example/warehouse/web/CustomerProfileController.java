package com.example.warehouse.web;

import com.example.warehouse.mapper.ProfileDAOMapper;
import com.example.warehouse.model.dao.AddressDAO;
import com.example.warehouse.model.dao.ProfileDAO;
import com.example.warehouse.model.dto.ProfileDTO;
import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.AddressRepository;
import com.example.warehouse.repository.ProfileRepository;
import com.example.warehouse.service.AddressService;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class CustomerProfileController {

    private final AuthService authService;
    private final ProfileRepository profileRepository;
    private final ProfileDAOMapper profileDAOMapper;

    private final AddressRepository addressRepository;
    private final ProfileService profileService;
    private final AddressService addressService;

    public CustomerProfileController(AuthService authService, ProfileRepository profileRepository, ProfileDAOMapper profileDAOMapper, AddressRepository addressRepository, ProfileService profileService, AddressService addressService) {
        this.authService = authService;
        this.profileRepository = profileRepository;
        this.profileDAOMapper = profileDAOMapper;
        this.addressRepository = addressRepository;
        this.profileService = profileService;
        this.addressService = addressService;
    }

    @ModelAttribute("profileDTO")
    public ProfileDTO initProfile() {
        return new ProfileDTO();
    }

//    @ModelAttribute("addresses")
//    public List<AddressDAO> customerAddresses(Authentication authentication) {
//        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
//        Profile currentProfile = profileRepository.findByUser(currentUser);
//        return addressRepository.findByProfile(currentProfile.getId());
//    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        Profile profile = profileRepository.findByUser(authService.getCurrentlyLoggedInCustomer(authentication));

        if (profile == null) {
            return "redirect:/users/profile/create";
        }

        ProfileDAO currentProfile = profileDAOMapper.apply(profile);
        List<AddressDAO> addressList = addressService.addressDAOList(profile);
        model.addAttribute(currentProfile);
        model.addAttribute("addresses",addressList);

        return "profile";

    }

    @GetMapping("/profile/create")
    public String profile() {

        return "profileCreate";
    }

    @PostMapping("/profile/create")
    public String createProfile(@Valid ProfileDTO profileDTO,
                                Authentication authentication,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileDTO", profileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profileDTO", bindingResult);

            return "redirect:/users/profile/create";
        }

        this.profileService.createProfile(currentUser, profileDTO);
        return "redirect:/users/profile";
    }
}
