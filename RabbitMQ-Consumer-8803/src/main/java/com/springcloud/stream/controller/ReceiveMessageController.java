package com.springcloud.stream.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : renjiahui
 * @date : 2021/1/9 1:33
 * @desc :
 */
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageController {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageController.class);

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        logger.info("消费者2号：---->接收到的消息：{} \t port:{}",
                message.getPayload(), serverPort);
    }
}
