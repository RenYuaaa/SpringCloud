package com.springcloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.entity.Payment;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;
import com.springcloud.sentinel.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: renjiahui
 * @date: 2021-02-21 23:18
 * @description:
 */
@RestController
public class RateLimitController {

    @GetMapping("/api/rate/limit/byResouce")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public Result byResource() {
        String message = "按资源名称限流测试OK";
        return new Result(CommonStatus.SUCCESS, new Payment(2020, "serial001"));
    }

    public Result handleException(BlockException exception) {
        return new Result(CommonStatus.UNKNOWN_ERROR, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @GetMapping("/api/rate/limit/byUrl")
    public Result byUrl() {
        return new Result(CommonStatus.SUCCESS, new Payment(2020, "serial002"));
    }

    //CustomerBlockHandler
    @GetMapping("/api/rate/limit/customer")
    @SentinelResource(value ="customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public Result customerBlockHandler() {
        return new Result(CommonStatus.SUCCESS, new Payment(2020, "serial003"));
    }
}
