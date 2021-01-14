package com.springcloud.alibaba.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : renjiahui
 * @date : 2021/1/14 22:33
 * @desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConfigClientApplication.class, args);
    }
}
