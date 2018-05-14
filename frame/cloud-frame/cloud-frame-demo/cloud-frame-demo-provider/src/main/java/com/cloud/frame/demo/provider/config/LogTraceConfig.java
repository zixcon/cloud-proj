package com.cloud.frame.demo.provider.config;

import com.cloud.frame.demo.constant.LogTraceConstant;
import com.google.common.base.Strings;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wd on 2018/5/10.
 */
@Configuration
public class LogTraceConfig {

    @Bean
    public FilterRegistrationBean logTraceFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(logTraceFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }

    public OncePerRequestFilter logTraceFilter() {

        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                try {
                    String traceId = request.getHeader(LogTraceConstant.TRACEID);
                    if (Strings.isNullOrEmpty(traceId)) {
                        traceId = UUID.randomUUID().toString();
                    }
                    MDC.put(LogTraceConstant.TRACEID, traceId);
                    filterChain.doFilter(request, response);
                } finally {
                    MDC.clear();
                }
            }
        };
    }
}
