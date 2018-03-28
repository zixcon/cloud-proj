package com.cloud.frame.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
//@EnableAdminServer
@SpringBootApplication
public class CloudFrameMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameMonitorApplication.class, args);
    }
}
