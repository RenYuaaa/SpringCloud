package com.springcloud.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : renjiahui
 * @date : 2021-02-01 22:45
 * @desc : 流控
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----------testB";
    }

}
