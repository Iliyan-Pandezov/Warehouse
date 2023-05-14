package com.example.warehouse.service;

import com.example.warehouse.mapper.AddressDAOMapper;
import com.example.warehouse.model.dto.AddressDTO;
import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.AddressRepository;
import com.example.warehouse.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;
    private final AddressDAOMapper addressDAOMapper;

    public AddressService(AddressRepository addressRepository, ProfileRepository profileRepository, AddressDAOMapper addressDAOMapper) {
        this.addressRepository = addressRepository;
        this.profileRepository = profileRepository;
        this.addressDAOMapper = addressDAOMapper;
    }

    public void createAddress(AddressDTO addressDTO, User currentUser) {

        Profile profile = profileRepository.findByUser(currentUser);

        Address address = new Address();

        address.setAddressName(addressDTO.addressName());
        address.setTown(addressDTO.town());
        address.setNeighbourhood(addressDTO.neighbourhood());
        address.setStreetName(addressDTO.streetName());
        address.setStreetNumber(addressDTO.streetNumber());
        address.setBuildingNumber(addressDTO.buildingNumber());
        address.setEntrance(addressDTO.entrance());
        address.setFloor(addressDTO.floor());
        address.setApartment(addressDTO.apartment());
        address.setProfile(profile);

        addressRepository.save(address);
    }


    public List<AddressDTO> addressDAOList(Profile profile) {
        List<Address> customerAddresses = addressRepository.findByProfile(profile.getId());

        return customerAddresses.stream()
                .map(a -> new AddressDTO(
                        a.getId(),
                        a.getProfile(),
                        a.getAddressName(),
                        a.getTown(),
                        a.getNeighbourhood(),
                        a.getStreetName(),
                        a.getStreetNumber(),
                        a.getBuildingNumber(),
                        a.getEntrance(),
                        a.getFloor(),
                        a.getApartment()
                ))
                .collect(Collectors.toList());

    }
}