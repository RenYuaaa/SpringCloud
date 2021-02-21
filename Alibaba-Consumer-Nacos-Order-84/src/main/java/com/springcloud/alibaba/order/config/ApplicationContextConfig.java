package com.springcloud.alibaba.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : renjiahui
 * @date : 2020/12/23 1:25
 * @desc :
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 使用Ribbon时加上@LoadBalanced注解
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
