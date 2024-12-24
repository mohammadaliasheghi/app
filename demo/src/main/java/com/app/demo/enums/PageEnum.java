package com.app.demo.enums;

import lombok.Getter;

@Getter
public enum PageEnum {

    USER("USER", "msg.enum.user"),
    PROJECT("PROJECT", "msg.enum.project");

    private final String title;
    private final String literal;

    PageEnum(String title, String literal) {
        this.title = title;
        this.literal = literal;
    }
}
