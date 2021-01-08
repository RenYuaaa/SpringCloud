package com.springcloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2021/1/8 23:44
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3366Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3366Application.class, args);
    }
}
