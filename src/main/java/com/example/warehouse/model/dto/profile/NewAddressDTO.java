package com.example.warehouse.model.dto.profile;

import com.example.warehouse.model.entity.Profile;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class NewAddressDTO {
    private Profile profile;
    @NotEmpty(message = "The address has to have a addressName.")
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
