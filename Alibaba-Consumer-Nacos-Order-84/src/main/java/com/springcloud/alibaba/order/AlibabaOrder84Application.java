package com.springcloud.alibaba.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : renjiahui
 * @date : 2021/1/12 22:13
 * @desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlibabaOrder84Application {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaOrder84Application.class, args);
    }
}
