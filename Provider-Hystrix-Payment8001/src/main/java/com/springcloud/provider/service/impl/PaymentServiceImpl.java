package com.springcloud.provider.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.provider.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author : renjiahui
 * @date : 2021/1/4 19:05
 * @desc :
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    /**
     * 正常访问OK的方法
     *
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id: "
                + id + "\t" + "..........";
    }

    /**
     * 访问超时的方法
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        Integer age = 10 / 0;
        Integer timeout = new Integer(5);
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            logger.info("错误信息：{}", e);
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: "
                + id + "\t" + ".........." + "耗时（s）：" + timeout;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙，请稍后再试 id: "
                + id + "\t" + "..........";
    }

    /**
     * 服务熔断
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakFallback", commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")

    })
    public String paymentCircuitBreak(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serilaNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serilaNumber;
    }

    /**
     * 服务熔断方法
     */
    public String paymentCircuitBreakFallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后重试";
    }
}
