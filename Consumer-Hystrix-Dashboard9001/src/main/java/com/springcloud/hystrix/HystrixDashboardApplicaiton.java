package com.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author : renjiahui
 * @date : 2021/1/4 21:36
 * @desc :
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplicaiton.class, args);
    }
}
