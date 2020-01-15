package com.ashish.weather.util;


import com.ashish.weather.model.JSONWeatherSet;
import com.ashish.weather.view.MainActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TestUtils {
    // private static Gson mGson;
    private static TestUtils INSTANCE = new TestUtils();

    public static <T> T loadJson(String path) {
        Gson mGson = new Gson();
        String json = getFileString(path);
        //noinspection unchecked
        return (T) mGson.fromJson(json, JSONWeatherSet.class);
    }

    public static <T> T loadggnJson(String path, Gson mGson) {
        mGson = new Gson();
        String json = getFileString(path);
        //noinspection unchecked
        return (T) mGson.fromJson(json, MainActivity.class);
    }

    private static String getFileString(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(INSTANCE.getClass().getClassLoader()).getResourceAsStream(path)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read from resource at: " + path);
        }
    }
}
