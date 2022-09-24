package com.example.warehouse.seeder;

import com.example.warehouse.model.entity.Role;
import com.example.warehouse.model.enums.UserRoleEnum;
import com.example.warehouse.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count()==0){
            List<Role> roles = Arrays.stream(UserRoleEnum.values())
                    .map(Role::new)
                    .collect(Collectors.toList());

            this.roleRepository.saveAll(roles);
        }
    }
}
