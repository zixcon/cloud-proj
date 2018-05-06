package com.cloud.frame.demo.auth.config;

import com.cloud.frame.demo.auth.interceptor.AuthInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 1. @EnableWebMvc=WebMvcConfigurationSupport，使用了@EnableWebMvc注解等于扩展了WebMvcConfigurationSupport但是没有重写任何方法
 * 2. 重写WebMvcConfigurationSupport方法，是不会自动加载的，因为：
 * springboot的web自动配置类 WebMvcAutoConfiguration 上有条件注解@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * Created by wd on 2018/5/3.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;


    /**
     * 添加权限拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[]{"/api/auth/login", "/*.html", "/swagger-resources/**", "/oauth/*"};
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

    /**
     * 添加swagger
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 添加添加GsonHttpMessageConverter，排除MappingJackson2HttpMessageConverter
     * http://blog.gelu.me/2017/spring-boot-uses-gson-to-replace-jackson/
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter); // 删除MappingJackson2HttpMessageConverter
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);
        converters.add(gsonHttpMessageConverter);
    }

}
