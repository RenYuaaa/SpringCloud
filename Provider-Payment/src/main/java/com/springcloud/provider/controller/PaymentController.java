package com.springcloud.provider.controller;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;
import com.springcloud.provider.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : renjiahui
 * @date : 2020/12/23 0:51
 * @desc :
 */
@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/api/payment/create")
    public Result<Integer> create(@RequestBody Payment payment) {
        Integer id = paymentService.create(payment);
        logger.info("服务端口号为：{}", serverPort);
        return new Result<>(CommonStatus.SUCCESS, id);
    }

    @GetMapping("/api/payment/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        logger.info("服务端口号为：{}", serverPort);
        return new Result<>(CommonStatus.SUCCESS, payment);
    }


    @GetMapping("/api/payment/discovery")
    public Object discovery() {

        //第一种方式
        List<String> services = discoveryClient.getServices();
        services.forEach(e -> logger.info("element: {}", e));

        //第二种方式
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT");
        instances.forEach(e -> logger.info(e.getServiceId() + "\t" + e.getHost() + "\t" + e.getPort() + "\t" + e.getUri()));

        return this.discoveryClient;
    }

    @GetMapping("/api/payment/loadbalance")
    public String getPaymentLoadBalance() {
        return serverPort;
    }

    @GetMapping("/api/payment/timeout")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.info("睡眠3秒钟");
        }
        return serverPort;
    }

    @GetMapping("/api/payment/zipkin")
    public String paymentZipkin() {
        return "Hi, I am PaymentZipkin server fallback";
    }
}
