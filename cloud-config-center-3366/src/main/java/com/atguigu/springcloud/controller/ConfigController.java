package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ConfigController {

    @Value("${config.info}")
    private String config;


    @GetMapping("/config")
    public String config(){
        return config+" 3366 port";
    }
}
