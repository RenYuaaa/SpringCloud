package com.springcloud.provider.service.impl;

import com.springcloud.common.entity.Payment;
import com.springcloud.provider.dao.PaymentDao;
import com.springcloud.provider.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : renjiahui
 * @date : 2020/12/23 0:46
 * @desc :
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Integer create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.getPaymentById(id);
    }
}
