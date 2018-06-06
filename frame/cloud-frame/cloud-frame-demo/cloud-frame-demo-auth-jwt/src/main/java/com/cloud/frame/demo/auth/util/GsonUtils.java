package com.cloud.frame.demo.auth.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by wd on 2018/5/10.
 */
public class GsonUtils {

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    private GsonUtils() {
    }

    public static Gson getInstance() {
        return gson;
    }

    public static <K, V> Map<K, V> toMap(String arg) {
        return gson.fromJson(arg,
                new TypeToken<Map<K, V>>() {
                }.getType());
    }


}
