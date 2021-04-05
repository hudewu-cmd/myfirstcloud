package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.impl.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        messageProvider.send();
    }
}
