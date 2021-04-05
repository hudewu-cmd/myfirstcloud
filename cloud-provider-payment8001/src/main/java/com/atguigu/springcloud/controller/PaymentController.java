package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.impl.PaymentSerivceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentSerivceImpl paymentSerivce;

    @Value("${server.port}")
    private String server_port;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<? extends Object> create(@RequestBody Payment payment) {
        Long id = paymentSerivce.create(payment);
        if(id > 0){
            return new CommonResult<Long>(200,"插入成功 server_port:"+server_port,payment.getId());
        }


        return new CommonResult(500,"插入失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Payment payment = paymentSerivce.getPaymentById(id);
        if(payment != null){
            return new CommonResult<Payment>(200,"获取成功 server_port:"+server_port,payment);
        }
        return new CommonResult<Payment>(500,"获取失败");
    }

    @GetMapping("/payment/discoveryClient")
    public  Object discoveryClient(){
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info(service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance serviceInstance : instances){
                log.info("host:"+serviceInstance.getHost()+" port:"+serviceInstance.getPort()+" uri:"+serviceInstance.getUri());
            }
        }

        return discoveryClient;
    }


    @GetMapping("/payment/zipkin")
    public String zipkin() {
        return "zipkin ----";
    }

}
