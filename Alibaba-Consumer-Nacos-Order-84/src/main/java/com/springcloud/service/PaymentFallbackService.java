package com.springcloud.service;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.Result;
import org.springframework.stereotype.Component;

/**
 * @author: renjiahui
 * @date: 2021-02-22 1:12
 * @description:
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public Result<Payment> paymentSql(Integer id) {
        return new Result<>(444, "服务降级放回--PaymentFallbackService", null);
    }
}