package com.cloud.frame.demo.consumer.fallback;

import com.cloud.frame.demo.consumer.client.DemoProviderClient1;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/3/28.
 */
@Component
public class DemoClient1Fallback implements DemoProviderClient1 {

    @Override
    public String getMessage(String var) {
        return "fail";
    }

    @Override
    public String hello() {
        return "fail";
    }
}
