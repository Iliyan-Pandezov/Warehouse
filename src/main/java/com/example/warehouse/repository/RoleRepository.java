package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>{

    Role getRoleById(Long id);

}
