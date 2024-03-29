//package com.springcloud.gateway.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;1111
//
//import java.util.Date;
//import java.util.Objects;
//
///**
// * @author : renjiahui
// * @date : 2021/1/8 20:02
// * @desc :
// */
//@Component
//public class MyGatewayFilter implements GlobalFilter, Ordered {
//    private static final Logger logger = LoggerFactory.getLogger(MyGatewayFilter.class);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        logger.info("come in MyGatewayFilter: " + new Date());
//        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
//        if (Objects.isNull(uname)) {
//            logger.info("用户名不能为空");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
