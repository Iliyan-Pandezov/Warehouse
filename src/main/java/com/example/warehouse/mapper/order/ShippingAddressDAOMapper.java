package com.example.warehouse.mapper.order;

import com.example.warehouse.model.dao.order.ShippingAddressDAO;
import com.example.warehouse.model.entity.order.ShippingAddress;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShippingAddressDAOMapper implements Function<ShippingAddress, ShippingAddressDAO> {
    @Override
    public ShippingAddressDAO apply(ShippingAddress shippingAddress) {
        return new ShippingAddressDAO(
                shippingAddress.getId(),
                shippingAddress.getTown(),
                shippingAddress.getNeighbourhood(),
                shippingAddress.getStreetName(),
                shippingAddress.getStreetNumber(),
                shippingAddress.getBuildingNumber(),
                shippingAddress.getEntrance(),
                shippingAddress.getFloor(),
                shippingAddress.getApartment()
        );
    }
}
