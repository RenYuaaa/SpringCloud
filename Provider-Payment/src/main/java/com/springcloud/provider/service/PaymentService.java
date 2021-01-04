package com.springcloud.provider.service;


import com.springcloud.common.entity.Payment;

/**
 * @author : renjiahui
 * @date : 2020/12/23 0:46
 * @desc :
 */
public interface PaymentService {

    Integer create(Payment payment);

    Payment  getPaymentById(Integer id);
}
