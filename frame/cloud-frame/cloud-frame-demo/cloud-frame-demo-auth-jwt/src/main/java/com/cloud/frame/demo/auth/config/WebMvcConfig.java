package com.cloud.frame.demo.auth.config;

import com.cloud.frame.demo.auth.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * 1. @EnableWebMvc=WebMvcConfigurationSupport，使用了@EnableWebMvc注解等于扩展了WebMvcConfigurationSupport但是没有重写任何方法
 * 2. 重写WebMvcConfigurationSupport方法，是不会自动加载的，因为：
 * springboot的web自动配置类 WebMvcAutoConfiguration 上有条件注解@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * Created by wd on 2018/5/3.
 */
@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[]{"/api/auth/login", "/*.html", "/swagger-resources/**", "/oauth/*"};
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
