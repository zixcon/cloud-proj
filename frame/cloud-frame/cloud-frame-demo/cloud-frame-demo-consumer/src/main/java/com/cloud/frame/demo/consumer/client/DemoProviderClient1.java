package com.cloud.frame.demo.consumer.client;

import com.cloud.frame.demo.consumer.fallback.DemoClient1Fallback;
import com.cloud.frame.demo.consumer.fallback.DemoClient1FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 实现容错机制：
 * 1. consumer端使用 @FeignClient
 * FallbackFactory可以获得回调触发的原因
 * 2. provider 端使用 @EnableCircuitBreaker + @HystrixCommand
 *
 * Created by wd on 2018/3/27.
 */
//@FeignClient(value = "cloud-frame-demo-provider", fallback = DemoClient1Fallback.class)
@FeignClient(value = "cloud-frame-demo-provider", fallbackFactory = DemoClient1FallbackFactory.class)
public interface DemoProviderClient1 {

    @RequestMapping(value = "/demo/provider/print/{var}", method = GET)
    String getMessage(@PathVariable(name = "var") String var);


    @RequestMapping(value = "/demo/provider/hello", method = GET)
    String hello();


}
