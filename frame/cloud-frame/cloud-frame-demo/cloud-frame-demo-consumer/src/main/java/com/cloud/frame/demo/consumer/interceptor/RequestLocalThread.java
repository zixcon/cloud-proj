package com.cloud.frame.demo.consumer.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wd on 2018/5/14.
 */
public class RequestLocalThread {

    private static ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new InheritableThreadLocal<>();

    public static void set(HttpServletRequest request) {
        REQUEST_LOCAL.set(request);
    }


    public static HttpServletRequest get() {
        return REQUEST_LOCAL.get();
    }


    public static void remove() {
        REQUEST_LOCAL.remove();
    }

}
