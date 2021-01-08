package com.springcloud.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : renjiahui
 * @date : 2021/1/8 23:45
 * @desc :
 */
@RestController
@RefreshScope
public class ConfilClientController {


    @Value("${config.info}")
    private String configInfo;

    /**
     * 获取配置中心的配置信息
     * 手动版同步数据中心信息：（自动同步需要用到bus）
     * 如果动态修改配置中心后要同步到这里，需要在本地配置中增加management配置
     * 然后再controller上增加@RefreshScope注解
     * 之后需要刷新post请求：curl -X POST "http://localhost:3355/actuator/refresh"
     * 这种方法可以避免服务重启
     */
    @GetMapping("/api/configclient/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
