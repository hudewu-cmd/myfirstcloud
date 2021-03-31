package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class OrederZkMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrederZkMain80.class,args);
    }
}
