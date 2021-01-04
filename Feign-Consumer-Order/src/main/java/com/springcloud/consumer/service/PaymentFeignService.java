package com.springcloud.consumer.service;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : renjiahui
 * @date : 2021/1/3 19:45
 * @desc :
 */
@Component
@FeignClient(value = "PROVIDER-PAYMENT")
public interface PaymentFeignService {

    @GetMapping("/api/payment/get/{id}")
    Result<Payment> getPaymentById(@PathVariable("id") Integer id);

    @GetMapping("/api/payment/timeout")
    String paymentFeignTimeOut();
}
