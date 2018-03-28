package com.cloud.frame.demo.provider.controller;

import com.cloud.frame.demo.provider.service.DemoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wd on 2018/3/27.
 */
@RestController
@RequestMapping("/demo/provider")
public class DemoProviderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private DemoProviderService demoProviderService;

    @RequestMapping("/hello")
    public String hello() {
        if (true) {
            throw new IllegalArgumentException("这就是个问题");
        }
        return discoveryClient.getServices().toString();
    }

    @GetMapping("/print/{var}")
    public String getMessage(@PathVariable String var) {
        return demoProviderService.getMessage(var);
    }

    @GetMapping("/boom")
    public Integer boom(Integer n) throws Exception {
        return this.demoProviderService.desireNumber(n);
    }


}
