package com.cloud.frame.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * What's the difference between EnableEurekaClient and EnableDiscoveryClient?
 * https://stackoverflow.com/questions/31976236/whats-the-difference-between-enableeurekaclient-and-enablediscoveryclient
 *
 * spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），
 * @EnableDiscoveryClient基于spring-cloud-commons
 * @EnableEurekaClient基于spring-cloud-netflix
 *
 * 源码分析：https://blog.csdn.net/u012734441/article/details/78315086
 *
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}
