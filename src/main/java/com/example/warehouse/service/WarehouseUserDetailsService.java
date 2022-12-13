package com.example.warehouse.service;

import com.example.warehouse.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        return userRepository.findByUsername(username)
                .map(u -> new User(
                        u.getUsername(),
                        u.getPassword(),
                        u.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
