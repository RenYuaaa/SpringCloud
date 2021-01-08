package com.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2021/1/9 1:12
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitmqProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderApplication.class, args);
    }
}
