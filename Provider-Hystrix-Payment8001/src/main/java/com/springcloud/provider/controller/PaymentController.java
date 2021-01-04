package com.springcloud.provider.controller;

import com.springcloud.provider.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : renjiahui
 * @date : 2021/1/4 19:00
 * @desc :
 */
@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    /**
     * 正常访问OK的方法
     */
    @GetMapping("/api/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        logger.info("result: {}", result);
        return result;
    }

    /**
     * 访问超时的方法
     */
    @GetMapping("/api/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        logger.info("result: {}", result);
        return result;
    }

    @GetMapping("/api/payment/circuit/{id}")
    public String paymentCircuitBreak(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreak(id);
        logger.info("result: {}", result);
        return result;
    }
}
