package com.cloud.frame.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class CloudFrameGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameGatewayApplication.class, args);
    }

//    @Bean
//    public PatternServiceRouteMapper serviceRouteMapper(){
//
//        // servicePattern: 指的是微服务的pattern
//
//        // routePattern: 指的是路由的pattern
//
//        // 当你访问/microservice-provider-user/v1 其实就是
//
//        // localhost:8040/v1/microservice-provider-user/user/1
//
//        return new PatternServiceRouteMapper(
//
//                "(?<name>^.+)-(?<version>v.+$)","${version}/${name}"
//
//        );
//
//    }

}
