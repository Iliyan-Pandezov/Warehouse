package com.example.warehouse.mapper;

import com.example.warehouse.model.dao.ProfileDAO;
import com.example.warehouse.model.entity.Profile;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileDAOMapper implements Function<Profile, ProfileDAO> {
    @Override
    public ProfileDAO apply(Profile profile) {
        return new ProfileDAO(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhoneNumber(),
                profile.getUser()
        );
    }
}
