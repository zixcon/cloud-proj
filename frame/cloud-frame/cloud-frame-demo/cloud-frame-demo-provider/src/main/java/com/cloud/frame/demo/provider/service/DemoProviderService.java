package com.cloud.frame.demo.provider.service;

import com.google.common.base.Strings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 *
 * Spring的三种Circuit Breaker
 * 熔断：http://www.60kb.com/post/90.html
 *
 * Created by wd on 2018/3/27.
 */
@Service
public class DemoProviderService {

    public String getMessage(String arg) {
        if (Strings.isNullOrEmpty(arg)) {
            return "nil";
        }
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return arg;
    }

    /**
     * 当random的数字大于某值时,线程睡n秒,然后抛出一个运行时异常
     *
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "desireNumberFallback")
    public Integer desireNumber(Integer n) throws Exception {
        System.out.println("calling desireNumber()");
        if (Math.random() > .5) {
            Thread.sleep(1000 * n);
            //throw new RuntimeException("Boom");
        }
        return 1;
    }

    public Integer desireNumberFallback(Integer n) throws Exception {
        return -1;
    }
}
