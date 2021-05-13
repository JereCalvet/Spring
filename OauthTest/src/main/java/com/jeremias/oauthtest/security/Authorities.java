package com.jeremias.oauthtest.security;

public enum Authorities {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_DELETE("user:delete");

    private final String permission;

    Authorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
