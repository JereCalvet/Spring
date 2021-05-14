package com.jeremias.oauthtest.models;

import com.jeremias.oauthtest.security.Roles;
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
    private final Roles role;

    public UserDto(String email, String password, Roles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }
}
