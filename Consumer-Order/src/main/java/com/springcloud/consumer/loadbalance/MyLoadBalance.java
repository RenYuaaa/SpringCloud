package com.springcloud.consumer.loadbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : renjiahui
 * @date : 2020/12/28 21:26
 * @desc :
 */
@Component
public class MyLoadBalance implements LoadBalancer {
    private static final Logger logger = LoggerFactory.getLogger(MyLoadBalance.class);

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        Integer index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    /**
     * 获取并增加
     * 采用了自旋锁，代替同步锁，但是要用Atomic保证原子性
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (this.atomicInteger.compareAndSet(current, next));
        logger.info("第{}次访问", next);
        return next;
    }
}
