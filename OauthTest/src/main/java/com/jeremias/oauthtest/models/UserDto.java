package com.jeremias.oauthtest.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private final String email;
    @NotNull(message = "Password is required.")
    @Length(min = 5)
    private final String password;
    @NotNull(message = "Roles is required.")
    private final String roles;

    public UserDto(String email, String password, String roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }
}
