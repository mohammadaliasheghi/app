package com.mmad.oauth.util;

import com.google.gson.Gson;

public class JSON<T> {
    private static final Gson gson = new Gson();

    private JSON() {
    }

    public static String convertToJSON(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T convertToObject(String json, Class<T> classObject) {
        return gson.fromJson(json, classObject);
    }
}
