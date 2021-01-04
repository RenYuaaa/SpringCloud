package com.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : renjiahui
 * @date : 2021/1/3 19:38
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.springcloud.consumer.service")
public class FeignOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApplication.class, args);
    }
}
