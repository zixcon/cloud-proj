package com.cloud.frame.demo.reactive.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Created by wd on 2018/4/11.
 */
@Configuration
@EnableWebFlux
public class WebConfig {



}
