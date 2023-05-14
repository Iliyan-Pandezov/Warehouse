package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.User;

import javax.validation.constraints.NotEmpty;

public record ProfileDTO(
        Long id,

        @NotEmpty(message = "First addressName has to be provided!")
        String firstName,

        @NotEmpty(message = "Last addressName has to be provided!")
        String lastName,

        @NotEmpty(message = "Phone number has to be provided")
        String phoneNumber,

        User user
) {

}
