package com.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: renjiahui
 * @date: 2021-02-22 0:10
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment9003Application {
    public static void main(String[] args) {
        SpringApplication.run(Payment9003Application.class, args);
    }
}
