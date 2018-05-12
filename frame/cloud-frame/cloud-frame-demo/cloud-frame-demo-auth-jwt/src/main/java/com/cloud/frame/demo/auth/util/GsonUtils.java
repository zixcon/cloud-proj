package com.cloud.frame.demo.auth.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
