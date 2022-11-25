package com.example.warehouse.service;

import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.model.dto.UserRegistrationDTO;
import com.example.warehouse.model.entity.Role;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public void register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("password.match");
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("username.used");
        }

        User newUser = userMapper.DTOToUser(userRegistrationDTO);
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        Role customRole = roleRepository.getRoleById(3L);
        newUser.addRole(customRole);

        this.userRepository.save(newUser);
    }
}
