package com.springcloud.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : renjiahui
 * @date : 2021/1/8 21:26
 * @desc :
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /**
     * 获取配置中心的配置信息
     * 自动版同步数据中心信息：（自动同步需要用到bus）
     * 在配置中心修改过配置之后，需要刷新post请求：curl -X POST "http://localhost:3344/actuator/bus-refresh"
     * 即可刷新全部服务的配置
     * 如果需要顶点刷新（比如只刷新3355服务而不刷新3366服务），则刷新的破石头请求为：
     * curl -X POST "http://localhost:配置中心端口号/actuator/bus-refresh/微服务名（spring.application.name）:需要刷新的服务端口号"
     * 这种方法可以避免服务重启
     */
    @GetMapping("/api/configclient/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
