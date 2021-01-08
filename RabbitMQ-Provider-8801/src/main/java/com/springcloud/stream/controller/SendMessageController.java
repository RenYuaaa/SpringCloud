package com.springcloud.stream.controller;

import com.springcloud.stream.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : renjiahui
 * @date : 2021/1/9 1:20
 * @desc :
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("/api/message/send")
    public String sendMessage() {
        return iMessageProvider.send();
    }
}
