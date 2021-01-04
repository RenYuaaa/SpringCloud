package com.springcloud.consumer.controller;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.Result;
import com.springcloud.consumer.service.PaymentFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : renjiahui
 * @date : 2021/1/3 19:40
 * @desc :
 */
@RestController
@RequestMapping(value = "/api/order")
public class PaymentFeignController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentFeignController.class);

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Integer id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/timeout")
    public String paymentFeignTimeOut() {
        return paymentFeignService.paymentFeignTimeOut();
    }
}

