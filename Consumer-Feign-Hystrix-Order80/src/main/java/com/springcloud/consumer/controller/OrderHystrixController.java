package com.springcloud.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.consumer.service.OrderHystrixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : renjiahui
 * @date : 2021/1/4 19:27
 * @desc :
 */
@RestController
@DefaultProperties(defaultFallback = "orderGlobalFallBackMethod")
public class OrderHystrixController {
    private static final Logger logger = LoggerFactory.getLogger(OrderHystrixService.class);

    @Resource
    private OrderHystrixService orderHystrixService;

    /**
     * 正常访问OK的方法
     */
    @GetMapping("/api/order/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = orderHystrixService.paymentInfo_OK(id);
        return result;
    }

    /**
     * 访问超时的方法
     */
    @GetMapping("/api/order/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "orderTimeOutFallBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = orderHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 独享的服务降级接口
     */
    private String orderTimeOutFallBackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙或自己运行出错，请稍后重试。";
    }

    /**
     * 全局服务降级接口
     * 注意：全局服务降级接口不能有参数
     */
    private String orderGlobalFallBackMethod() {
        return "Global异常处理信息，请稍后重试!";
    }
}
