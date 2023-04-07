package com.example.warehouse.mapper;

import com.example.warehouse.model.dao.AddressDAO;
import com.example.warehouse.model.entity.Address;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressDAOMapper implements Function<Address, AddressDAO> {
    @Override
    public AddressDAO apply(Address address) {

        return new AddressDAO(
                address.getId(),
                address.getProfile(),
                address.getName(),
                address.getTown(),
                address.getNeighbourhood(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getBuildingNumber(),
                address.getEntrance(),
                address.getFloor(),
                address.getApartment()
        );
    }
}
