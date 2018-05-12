package com.cloud.frame.demo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class CloudFrameDemoAuthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameDemoAuthJwtApplication.class, args);
    }
}
