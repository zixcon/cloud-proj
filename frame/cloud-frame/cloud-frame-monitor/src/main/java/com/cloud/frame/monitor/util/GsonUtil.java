package com.cloud.frame.monitor.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by wd on 2018/5/13.
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
