package com.cloud.frame.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CloudFrameRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameRegisterApplication.class, args);
    }
}
