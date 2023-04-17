package com.example.warehouse.web;

import com.example.warehouse.mapper.ProfileDAOMapper;
import com.example.warehouse.model.dao.AddressDAO;
import com.example.warehouse.model.dao.ProfileDAO;
import com.example.warehouse.model.entity.Cart;
import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.ProfileRepository;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.AddressService;
import com.example.warehouse.service.AuthService;
import com.example.warehouse.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class CartController {

    private final CartService cartService;

    private final AuthService authService;

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final ProfileDAOMapper profileDAOMapper;
    private final ProfileRepository profileRepository;

    public CartController(CartService cartService, AuthService authService, UserRepository userRepository, AddressService addressService, ProfileDAOMapper profileDAOMapper, ProfileRepository profileRepository) {
        this.cartService = cartService;
        this.authService = authService;
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.profileDAOMapper = profileDAOMapper;
        this.profileRepository = profileRepository;
    }

    @GetMapping("/cart")
    public String listOfProducts(Model model, Authentication authentication) {


        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
        Profile profile = profileRepository.findByUser(authService.getCurrentlyLoggedInCustomer(authentication));
        ProfileDAO currentProfile = profileDAOMapper.apply(profile);
        List<AddressDAO> addressList = addressService.addressDAOList(profile);
        List<Cart> cartItems = cartService.listCartItems(currentUser);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("addresses", addressList);
        return "cart";
    }

//    @PostMapping("/cart/delete/{id}")
//    public String removeAProduct(@PathVariable("id") Integer cartItemId,
//                                 Authentication authentication) {
//
//        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
//        cartService.removeAProduct(cartItemId, currentUser);
//
//        return "redirect:/users/cart";
//    }

//    @PostMapping("/cart/add/{id}/{quantity}")
//    public String addProductToCart(@PathVariable("id") Long productId,
//                                   @PathVariable("quantity") Integer quantity,
//                                   Authentication authentication) {
//
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "You must log in in order to add this product to your cart!";
//        }
//
//        User currentUser = authService.getCurrentlyLoggedInCustomer(authentication);
//
//        Integer addedQuantity = cartService.addProduct(productId, quantity, currentUser);
//
//        return addedQuantity + " item(s) were added to your cart.";
//    }
}
