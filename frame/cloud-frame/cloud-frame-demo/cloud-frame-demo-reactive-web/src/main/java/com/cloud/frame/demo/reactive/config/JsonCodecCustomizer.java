package com.cloud.frame.demo.reactive.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by wd on 2018/4/13.
 */
@Configuration
public class JsonCodecCustomizer {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public HttpMessageConverter httpMessageConverter(Gson gson) {
//        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//        gsonHttpMessageConverter.setGson(gson);
//        return gsonHttpMessageConverter;
//    }

//    @Bean
//    public CodecCustomizer myCodecCustomizer() {
//        return codecConfigurer -> {
//            codecConfigurer.customCodecs().reader();
//        };
//    }

}
