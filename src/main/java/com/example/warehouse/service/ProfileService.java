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

        // TODO check if profile is empty or not!

        Profile profile = profileRepository.findByUser(user);

        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
        }
        if (profileDTO.firstName() != null) {
            profile.setFirstName(profileDTO.firstName());
        }
        if (profileDTO.lastName() != null) {
            profile.setLastName(profileDTO.lastName());
        }
        if (profileDTO.phoneNumber() != null) {
            profile.setPhoneNumber(profileDTO.phoneNumber());
        }

        profileRepository.save(profile);
    }
}
