package com.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2021/1/8 20:41
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }
}
