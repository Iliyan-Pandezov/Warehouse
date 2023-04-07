package com.example.warehouse.model.dao;

import com.example.warehouse.model.entity.Profile;


public record AddressDAO(
        Long id,

        Profile profile,

        String name,

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
