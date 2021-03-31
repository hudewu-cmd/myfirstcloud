package com.atguigui.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigui.springcloud.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class FeignController {


    @Autowired
    FeignService feignService;
    @RequestMapping(value = "/consumer/payment/feign/{id}")
    public CommonResult<Payment> paymentConsul(@PathVariable("id") Long id) {

        CommonResult<Payment> payment = feignService.getPaymentById(id);
        return payment;
    }
}
