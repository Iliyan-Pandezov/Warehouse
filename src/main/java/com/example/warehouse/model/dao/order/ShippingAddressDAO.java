package com.example.warehouse.model.dao.order;

import com.example.warehouse.model.entity.Profile;

public record ShippingAddressDAO(
        Long id,

        String town,

        String neighbourhood,

        String streetName,

        String streetNumber,

        String buildingNumber,

        String entrance,

        String floor,

        String apartment
) {
}
