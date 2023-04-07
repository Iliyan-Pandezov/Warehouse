package com.example.warehouse.service;

import com.example.warehouse.model.dto.ProfileDTO;
import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void createProfile(User user, ProfileDTO profileDTO) {

        Profile profile = profileRepository.findByUser(user);

        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
        }
        if (profileDTO.getFirstName() != null) {
            profile.setFirstName(profileDTO.getFirstName());
        }
        if (profileDTO.getLastName() != null) {
            profile.setLastName(profileDTO.getLastName());
        }
        if (profileDTO.getPhoneNumber() != null) {
            profile.setPhoneNumber(profileDTO.getPhoneNumber());
        }

        profileRepository.save(profile);
    }
}
