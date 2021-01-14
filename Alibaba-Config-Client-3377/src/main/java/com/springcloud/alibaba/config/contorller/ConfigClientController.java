package com.springcloud.alibaba.config.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : renjiahui
 * @date : 2021/1/14 22:34
 * @desc :
 */
@RestController
//支持Nacos的动态刷新
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/api/config/get")
    public String getConfigInfo() {
        return configInfo;
    }
}
