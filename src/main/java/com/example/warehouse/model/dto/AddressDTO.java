package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Profile;
import lombok.Data;


@Data
public class AddressDTO {

    private Profile profile;

    private String name;

    private String town;

    private String neighbourhood;

    private String streetName;

    private String streetNumber;

    private String buildingNumber;

    private String entrance;

    private String floor;

    private String apartment;

}
