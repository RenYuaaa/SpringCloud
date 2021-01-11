package com.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2021/1/9 1:32
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitmqConsumer8803Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqConsumer8803Application.class, args);
    }
}
