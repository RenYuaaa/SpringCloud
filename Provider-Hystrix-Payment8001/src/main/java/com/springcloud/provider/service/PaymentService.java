package com.springcloud.provider.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : renjiahui
 * @date : 2021/1/4 18:52
 * @desc :
 */
public interface PaymentService {


    /**
     * 正常访问OK的方法
     */
    String paymentInfo_OK(Integer id);

    /**
     * 访问超时的方法
     */
    String paymentInfo_TimeOut(Integer id);

    /**
     * 服务熔断
     */
    String paymentCircuitBreak(Integer id);
}
