package com.springcloud.consumer.controller;

import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;
import com.springcloud.consumer.loadbalance.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author : renjiahui
 * @date : 2020/12/23 1:23
 * @desc :
 */
@RestController
@RequestMapping("/api/order")
public class OrderContrller {
    private static final Logger logger = LoggerFactory.getLogger(OrderContrller.class);

    private static final String PAYMENT_URL = "http://PROVIDER-PAYMENT";

    private static final String SERVICE_NAME = "PROVIDER-PAYMENT";

    @Resource
    private RestTemplate restTemplate;
    /**
     * 自己些的负载均衡
     */
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public Result<Integer> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/api/payment/create", payment, Result.class);
    }

    @GetMapping("/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/api/payment/get/" + id, Result.class);
    }

    @GetMapping("/entity/{id}")
    public Result<Payment> getPaymentEntity(@PathVariable("id") Integer id) {
        ResponseEntity<Result> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/api/payment/get/" + id, Result.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new Result<>(CommonStatus.SUCCESS);
        }
    }

    @GetMapping("/loadbalance")
    public String getPaymentLoadBalance() {
        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_NAME);
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/loadbalance", String.class);
    }
}
