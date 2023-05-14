package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Profile;

import javax.validation.constraints.NotEmpty;

public record AddressDTO (
        Long id,

        Profile profile,

        @NotEmpty(message = "Name has to be provided!")
        String addressName,

        String town,

        String neighbourhood,

        String streetName,

        String streetNumber,

        String buildingNumber,

        String entrance,

        String floor,

        String apartment
){

}
