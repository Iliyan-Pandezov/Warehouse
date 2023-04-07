package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class ProfileDTO {
    @NotEmpty(message = "First name has to be provided!")
    private String firstName;

    @NotEmpty(message = "Last name has to be provided!")
    private String lastName;

    @NotEmpty(message = "Phone number has to be provided")
    private String phoneNumber;

    private String town;

    private String neighbourhood;

    private String streetName;

    private String streetNumber;

    private String buildingNumber;

    private User user;

}
