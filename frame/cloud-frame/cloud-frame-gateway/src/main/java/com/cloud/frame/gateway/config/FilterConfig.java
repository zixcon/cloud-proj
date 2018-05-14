package com.cloud.frame.gateway.config;

import com.cloud.frame.gateway.filter.AuthorizationFilter;
import com.cloud.frame.gateway.filter.LogTraceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wd on 2018/5/10.
 */
@Configuration
public class FilterConfig {

    @Bean
    public AuthorizationFilter authenticationFilter() {
        return new AuthorizationFilter();
    }

    @Bean
    public LogTraceFilter logTraceFilter() {
        return new LogTraceFilter();
    }
}
