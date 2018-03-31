package com.cloud.frame.demo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

/**
 * Created by wd on 2018/3/30.
 */
@Configuration
public class SessionRedisConfig {

//    @Bean
//    public RequestCache requestCache() {
//        return new HttpSessionRequestCache();
//    }
//
//    @Bean
//    public RedirectStrategy redirectStrategy(){
//        return new DefaultRedirectStrategy();
//    }
}
