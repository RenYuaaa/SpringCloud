package com.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2020/12/13 18:56
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentProvider8002Application {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProvider8002Application.class, args);
    }
}
