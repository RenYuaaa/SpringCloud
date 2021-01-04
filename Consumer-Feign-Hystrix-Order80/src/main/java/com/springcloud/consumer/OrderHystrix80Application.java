package com.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : renjiahui
 * @date : 2021/1/4 19:25
 * @desc :
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrix80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrix80Application.class, args);
    }
}
