package com.example.warehouse.model.dto;

import javax.validation.constraints.*;

public class UserRegistrationDTO {
    @NotEmpty(message = "User email has to be provided!")
    @Email(message = "Has to be a valid email address!")
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 10)
    private String password;

    @NotNull
    @Size(min = 6, max = 10)
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
