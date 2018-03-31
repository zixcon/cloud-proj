package com.cloud.frame.demo.consumer.controller;

import com.cloud.frame.demo.consumer.client.DemoProviderClient1;
import com.cloud.frame.demo.consumer.client.DemoProviderClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wd on 2018/3/27.
 */
@RestController
@RequestMapping("/demo/consumer")
public class DemoConsumerController {


    @Autowired
    private DemoProviderClient1 demoProviderClient1;

    @Autowired
    private DemoProviderClient2 demoProviderClient2;

    @GetMapping("/print/{var}")
    public String getMessage(@PathVariable String var) {
        return demoProviderClient1.getMessage(var);
    }

    @GetMapping("/hello")
    public String hello() {
        return demoProviderClient1.hello();
    }

    @GetMapping("/hello/do")
    public String helloDo() {
        String hello = demoProviderClient2.hello();
        String result = "sdf";
        return result + hello;
    }

    @GetMapping("/zuul/hello")
    public String zuulHello() {
        return demoProviderClient2.zuulHello();
    }

    @GetMapping("/boom")
    public Integer desireNumber(Integer n) throws Exception {
        return demoProviderClient2.desireNumber(n);
    }
}
