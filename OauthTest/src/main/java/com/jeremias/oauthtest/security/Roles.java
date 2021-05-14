package com.jeremias.oauthtest.security;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static com.jeremias.oauthtest.security.Authorities.*;
import static java.util.stream.Collectors.*;

public enum Roles {
    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, USER_DELETE));

    private final Set<Authorities> permissions;

    Roles(Set<Authorities> permissions) {
        this.permissions = permissions;
    }

    public Set<Authorities> getPermissions() {
        return permissions;
    }

    @JsonValue
    public String getRole() {
        return this.name();
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        final Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
