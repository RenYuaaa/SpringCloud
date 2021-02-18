package com.springcloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author : renjiahui
 * @date : 2021-02-01 22:45
 * @desc : 流控
 */
@RestController
public class FlowLimitController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/testA")
    public String testA() {
        return "----------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----------testB";
    }

    @GetMapping("/testD")
    public String testD() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        logger.info("testD 测试RT");

        logger.info("testD异常比例");
        int age = 1 / 0;
        return "----------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        logger.info("testE 测试异常数");
        int age = 10 / 0;
        return "----------testE 测试异数";
    }

    /**
     * SentinelResource
     *      value中的值要唯一，可以不用是方法名，但要唯一
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "----------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        //sentinel系统默认的提示：Blocked by Sentinel（flow limiting）
        return "----------deal_testHotKey";
    }
}
