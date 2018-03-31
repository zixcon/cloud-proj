package com.cloud.frame.demo.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by wd on 2018/3/30.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
//                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
