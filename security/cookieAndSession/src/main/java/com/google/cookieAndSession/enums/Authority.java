package com.google.cookieAndSession.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    OP_ACCESS_ADMIN,
    OP_DELETE_USER,
    OP_GET_USER,
    OP_ACCESS_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
