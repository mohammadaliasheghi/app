package com.mmad.oauth.util;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public final class ResponseDto<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7223531739271195121L;
    String message;
    T responseObj;
    Date date;

    public ResponseDto(String message, T responseObj) {
        this.message = message;
        this.responseObj = responseObj;
        this.date = new Date();
    }

    public ResponseDto(String message) {
        this.message = message;
        this.date = new Date();
    }
}
