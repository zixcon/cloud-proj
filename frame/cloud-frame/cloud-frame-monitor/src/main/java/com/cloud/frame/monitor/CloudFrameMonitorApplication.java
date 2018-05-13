package com.cloud.frame.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//@EnableTurbine
//@EnableHystrix
//@EnableHystrixDashboard
@EnableDiscoveryClient
//@ImportAutoConfiguration(classes = {de.codecentric.boot.admin.config.RevereseZuulProxyConfiguration.class})
//@ComponentScan("de.codecentric.boot.admin")
//@EnableAutoConfiguration(exclude = {ZuulServerAutoConfiguration.class})
//@EnableAdminServer
@SpringBootApplication
public class CloudFrameMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameMonitorApplication.class, args);
    }
}
