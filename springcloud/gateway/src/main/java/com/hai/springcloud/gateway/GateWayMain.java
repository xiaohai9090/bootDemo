package com.hai.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.hai.springcloud")
@EnableDiscoveryClient
public class GateWayMain {

    public static void main(String[] args) {
        SpringApplication.run(GateWayMain.class, args);
    }
}
