package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.rule.MySelfRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {


    @Value("${PAYMENT_URL}")
    private String PAYMENT_URL;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private MySelfRule mySelfRule;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, CommonResult.class);
        return commonResult;
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        log.info(id+"");
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
        return commonResult;
    }


    @GetMapping("/consumer/payment2/get/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        log.info(id+"");
//        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
//        return commonResult;
        ResponseEntity<CommonResult> entity= restTemplate.getForEntity(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
        CommonResult body = entity.getBody();
        return body;
    }

    @GetMapping("/consumer/myRule/get/{id}")
    public CommonResult<Payment> myRule(@PathVariable("id") Long id){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        ServiceInstance serviceInstance = mySelfRule.choose(instances);
        ResponseEntity<CommonResult> entity= restTemplate.getForEntity(serviceInstance.getUri() + "payment/get/" + id, CommonResult.class);
        CommonResult body = entity.getBody();
        return body;
    }


    @GetMapping("/consumer/payment/zipkin")
    public String zipkin(){
          return restTemplate.getForObject(PAYMENT_URL + "payment/zipkin",String.class);
    }

}
