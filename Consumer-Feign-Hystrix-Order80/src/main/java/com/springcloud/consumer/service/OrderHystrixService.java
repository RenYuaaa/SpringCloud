package com.springcloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : renjiahui
 * @date : 2021/1/4 19:27
 * @desc :
 */
@Component
@FeignClient(value = "PROVIDER-HYSTRIX-PAYMENT", fallback = OrderFallbackService.class)
public interface OrderHystrixService {

    /**
     * 正常访问OK的方法
     */
    @GetMapping("/api/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    /**
     * 访问超时的方法
     */
    @GetMapping("/api/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
