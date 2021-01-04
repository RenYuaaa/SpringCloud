package com.springcloud.consumer.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author : renjiahui
 * @date : 2020/12/28 21:24
 * @desc : 自己的负载均衡算法
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
