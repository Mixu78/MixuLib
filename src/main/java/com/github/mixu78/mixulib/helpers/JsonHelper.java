package com.github.mixu78.mixulib.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Nonnull;

public class JsonHelper {

    public Object fromJson(@Nonnull String json, @Nonnull Class type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public String toJson(@Nonnull Object toJson, boolean prettyprint) {
        Gson gson = prettyprint ? new GsonBuilder().setPrettyPrinting().create() : new GsonBuilder().create();
        return gson. toJson(toJson);
    }

    public String toJson(@Nonnull Object toJson) {
        Gson gson = new GsonBuilder().create();
        return gson. toJson(toJson);
    }
}
