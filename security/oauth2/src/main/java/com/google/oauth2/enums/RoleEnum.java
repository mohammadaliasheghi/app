package com.google.oauth2.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {

    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
