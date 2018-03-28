package com.cloud.frame.demo.consumer.fallback;

import com.cloud.frame.demo.consumer.client.DemoProviderClient1;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/3/28.
 */
@Component
public class DemoClient1FallbackFactory implements FallbackFactory<DemoProviderClient1> {
    @Override
    public DemoProviderClient1 create(Throwable throwable) {
        return new DemoProviderClient1() {
            @Override
            public String getMessage(String var) {
                return "fail reason: " + throwable.getMessage();
            }

            @Override
            public String hello() {
                return "fail reason: " + throwable.getMessage();
            }

        };
    }
}
