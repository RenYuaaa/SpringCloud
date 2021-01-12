package com.springcloud.alibaba.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : renjiahui
 * @date : 2021/1/12 22:13
 * @desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaOrder83Application {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaOrder83Application.class, args);
    }
}
