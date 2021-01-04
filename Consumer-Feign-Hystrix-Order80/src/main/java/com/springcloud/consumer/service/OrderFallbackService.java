package com.springcloud.consumer.service;

import org.springframework.stereotype.Component;

/**
 * @author : renjiahui
 * @date : 2021/1/4 20:20
 * @desc :
 */
@Component
public class OrderFallbackService implements OrderHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "OrderFallbackService: paymentInfo_OK fall back";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "OrderFallbackService: paymentInfo_TimeOut fall back";
    }
}
