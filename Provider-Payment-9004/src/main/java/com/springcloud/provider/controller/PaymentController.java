package com.springcloud.provider.controller;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author: renjiahui
 * @date: 2021-02-22 0:03
 * @description:
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Integer, Payment> map = new HashMap<>();

    static {
        map.put(1, new Payment(1, "123"));
        map.put(2, new Payment(2, "456"));
        map.put(3, new Payment(3, "789"));
        map.put(4, new Payment(4, "098"));
    }

    @GetMapping("/api/paymentSql/{id}")
    public Result<Payment> paymentSql(@PathVariable("id") Integer id) {
        Payment payment = map.get(id);
        return new Result<>(CommonStatus.SUCCESS, payment);
    }
}
