package com.cloud.frame.gateway.filter;

import com.netflix.zuul.context.RequestContext;

/**
 * Created by wd on 2018/5/14.
 */
public class RequestLocalThread {

    private static ThreadLocal<RequestContext> REQUEST_LOCAL = new InheritableThreadLocal<>();

    public static void set(RequestContext request) {
        REQUEST_LOCAL.set(request);
    }


    public static RequestContext get() {
        return REQUEST_LOCAL.get();
    }


    public static void remove() {
        REQUEST_LOCAL.remove();
    }

}
