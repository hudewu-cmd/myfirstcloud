package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.service.IMessageProvider;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageProvider  implements IMessageProvider {

    @Resource
    private MessageChannel output;


    public void  send() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        output.send(MessageBuilder.withPayload(uuid).build());
    }
}
