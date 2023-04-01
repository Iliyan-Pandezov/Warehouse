package com.example.warehouse.service;

import com.example.warehouse.model.entity.User;
import com.example.warehouse.model.entity.WarehouseUserDetails;
import com.example.warehouse.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class WarehouseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public WarehouseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user" + username);
        }

        return new WarehouseUserDetails(user);
    }
}
