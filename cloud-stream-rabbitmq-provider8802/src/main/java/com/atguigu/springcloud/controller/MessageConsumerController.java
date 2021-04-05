package com.atguigu.springcloud.controller;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding({Sink.class})
public class MessageConsumerController {

    @StreamListener(Sink.INPUT)
    public void recive(Message message){
        System.out.println(message.getPayload());
    }
}
