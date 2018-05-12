package com.cloud.frame.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by wd on 2018/5/11.
 */
public class GsonUtil {

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    private GsonUtil() {
    }

    public static Gson getInstance() {
        return gson;
    }
}
