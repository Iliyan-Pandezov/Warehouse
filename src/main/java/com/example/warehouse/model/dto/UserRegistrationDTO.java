package com.example.warehouse.model.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRegistrationDTO {
    @NotEmpty(message = "User email has to be provided!")
    @Email(message = "Has to be a valid email address!")
    @Size(max = 30)
    private String username;

    @NotNull()
    @Size(min = 6, max = 10, message = "Password must be at least 6 characters.")
    private String password;

    @NotNull
    @Size(min = 6, max = 10, message = "Passwords must match.")
    private String confirmPassword;
}
