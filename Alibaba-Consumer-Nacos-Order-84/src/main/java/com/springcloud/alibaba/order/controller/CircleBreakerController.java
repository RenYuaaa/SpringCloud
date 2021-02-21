package com.springcloud.alibaba.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;
import com.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: renjiahui
 * @date: 2021-02-22 0:28
 * @description:
 */
@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://NACOS-PAYMENT-PROVIDER";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/api/comsumer/fallback/{id}")
    //没有配置
//    @SentinelResource(value = "fallback")
    //fallback只负责业务夜场
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    //blockHandler只负责sentinel控制台配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            //下面的意思是：假如报这个异常，不再有fallback方法兜底，没有降级效果
            exceptionsToIgnore = {IllegalArgumentException.class})
    public Result<Payment> fallback(@PathVariable("id") Integer id) {
        Result result = restTemplate.getForObject(SERVICE_URL + "/api/paymentSql/" + id, Result.class, id);

        if (id >= 5) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (Objects.isNull(result.getData())) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录");
        }

        return result;
    }

    public Result handlerFallback(@PathVariable("id") Integer id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new Result(444, "handlerException, exception内容" + e.getMessage(), payment);
    }

    public Result blockHandler(@PathVariable("id") Integer id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new Result(445, "handler-sentinel限流，无此流水：blockException" + blockException.getRuleLimitApp(), payment);
    }

    @GetMapping("/api/consumer/payment/{id}")
    public Result<Payment> paymentSql(@PathVariable("id") Integer id) {
        return paymentService.paymentSql(id);
    }

}
