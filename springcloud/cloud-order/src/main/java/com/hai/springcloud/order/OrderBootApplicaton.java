package com.hai.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderBootApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(OrderBootApplicaton.class, args);
    }
}
