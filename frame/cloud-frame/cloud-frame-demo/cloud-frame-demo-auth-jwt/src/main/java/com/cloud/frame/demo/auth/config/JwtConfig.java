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
    @Value("${jwt.accessSecret}")
    private String accessSecret;
    @Value("${jwt.accessExpiration}")
    private String accessExpiration;
    @Value("${jwt.refreshSecret}")
    private String refreshSecret;
    @Value("${jwt.refreshExpiration}")
    private String refreshExpiration;

    public String getHeader() {
        return header;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public String getAccessExpiration() {
        return accessExpiration;
    }

    public String getRefreshSecret() {
        return refreshSecret;
    }

    public String getRefreshExpiration() {
        return refreshExpiration;
    }
}
