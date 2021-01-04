package com.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : renjiahui
 * @date : 2020/12/23 1:21
 * @desc :
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "PROVIDER-PAYMENT", configuration = MyselfRule.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
