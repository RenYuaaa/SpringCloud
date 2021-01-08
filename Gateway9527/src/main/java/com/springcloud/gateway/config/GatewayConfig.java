package com.springcloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : renjiahui
 * @date : 2021/1/8 19:04
 * @desc : 代码的方式配置gateway
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route_name的路由规则；
     * 当访问地址为http://localhost:9527/guonei时会自动转发到地址http://news.baidu.com/guonei
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("route_name", r -> r.path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }
}
