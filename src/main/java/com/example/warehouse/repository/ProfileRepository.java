package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
//    Optional<Profile> findByUser(User user);
}
