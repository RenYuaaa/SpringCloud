package com.springcloud.service;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: renjiahui
 * @date: 2021-02-22 1:12
 * @description:
 */
@FeignClient(value = "NACOS-PAYMENT-PROVIDER", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/api/paymentSql/{id}")
    Result<Payment> paymentSql(@PathVariable("id") Integer id);
}
