package com.cloud.frame.demo.consumer.client;

/**
 * Created by wd on 2018/3/28.
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "cloud-frame-demo-provider")
public interface DemoProviderClient2 {

    @RequestMapping(value = "/demo/provider/boom", method = GET)
    Integer desireNumber(@RequestParam(value = "n", defaultValue = "0") Integer n) throws Exception;
}
