package com.jeremias.oauthtest.models;

import com.jeremias.oauthtest.security.Roles;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String email;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Roles role;

    public User() {
    }

    public User(String email, String password, Roles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }
}
