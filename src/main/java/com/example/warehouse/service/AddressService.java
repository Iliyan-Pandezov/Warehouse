package com.example.warehouse.service;

import com.example.warehouse.mapper.AddressDAOMapper;
import com.example.warehouse.model.dao.AddressDAO;
import com.example.warehouse.model.dto.AddressDTO;
import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.Profile;
import com.example.warehouse.model.entity.User;
import com.example.warehouse.repository.AddressRepository;
import com.example.warehouse.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        address.setName(addressDTO.getName());
        address.setTown(addressDTO.getTown());
        address.setNeighbourhood(addressDTO.getNeighbourhood());
        address.setStreetName(addressDTO.getStreetName());
        address.setStreetNumber(addressDTO.getStreetNumber());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setEntrance(addressDTO.getEntrance());
        address.setFloor(addressDTO.getFloor());
        address.setApartment(addressDTO.getApartment());
        address.setProfile(profile);

        addressRepository.save(address);
    }

    public List<AddressDAO> addressDAOList(Profile profile) {
        List<Address> customerAddresses = addressRepository.findByProfile(profile.getId());

        return customerAddresses.stream()
                .map(a-> new AddressDAO(
                        a.getId(),
                        a.getProfile(),
                        a.getName(),
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



//        List<AddressDAO> list = new ArrayList<>();

//        for (Address address : customerAddresses) {
//            AddressDAO addressDAO = new AddressDAO(
//                    address.getId(),
//                    address.getProfile(),
//                    address.getName(),
//                    address.getTown(),
//                    address.getNeighbourhood(),
//                    address.getStreetName(),
//                    address.getStreetNumber(),
//                    address.getBuildingNumber(),
//                    address.getEntrance(),
//                    address.getFloor(),
//                    address.getApartment());
//            list.add(addressDAO);
//        }

//        return list;
    }
}