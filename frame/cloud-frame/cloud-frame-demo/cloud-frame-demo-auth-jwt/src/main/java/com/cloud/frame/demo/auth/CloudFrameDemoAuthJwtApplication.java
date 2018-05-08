package com.cloud.frame.demo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudFrameDemoAuthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameDemoAuthJwtApplication.class, args);
    }
}
