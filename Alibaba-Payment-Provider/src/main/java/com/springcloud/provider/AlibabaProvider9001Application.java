package com.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : renjiahui
 * @date : 2021/1/12 21:56
 * @desc :
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaProvider9001Application {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaProvider9001Application.class, args);
    }
}
