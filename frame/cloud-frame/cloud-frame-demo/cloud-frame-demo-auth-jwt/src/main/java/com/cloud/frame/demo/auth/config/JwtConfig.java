package com.cloud.frame.demo.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wd on 2018/5/4.
 */
@Configuration
public class JwtConfig {

    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;

    public String getHeader() {
        return header;
    }

    public String getSecret() {
        return secret;
    }

    public String getExpiration() {
        return expiration;
    }

}
