package com.jeremias.oauthtest.auth;

import com.jeremias.oauthtest.models.User;
import com.jeremias.oauthtest.security.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final String password;
    private final String username;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnable;

    public UserDetailsImpl(User user) {
        this.password = user.getPassword();
        this.username = user.getEmail();
        this.grantedAuthorities = getAllGrantedAuthoritiesFromEachRolesOfTheUser(user);
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnable = isEnable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("test"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Set<? extends GrantedAuthority> getAllGrantedAuthoritiesFromEachRolesOfTheUser(User user) {
        final Set<GrantedAuthority> allPermissions = new HashSet<>();
        user.getRoles()
                .forEach(rol -> allPermissions.addAll(rol.getGrantedAuthorities()));
        return allPermissions;
    }
}
