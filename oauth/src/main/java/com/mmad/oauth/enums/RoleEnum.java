package com.mmad.oauth.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ROLE_ADMIN(Name.ROLE_ADMIN),
    ROLE_USER(Name.ROLE_USER);

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public static class Name {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";
    }
}
