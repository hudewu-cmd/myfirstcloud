package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.impl.PaymentSerivceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PaymentController {

    @Resource
    private PaymentSerivceImpl paymentSerivce;

    @Value("${server.port}")
    private String server_port;

    @PostMapping("/payment/create")
    public CommonResult<? extends Object> create(@RequestBody Payment payment) {
        Long id = paymentSerivce.create(payment);
        if(id > 0){
            return new CommonResult<Long>(200,"插入成功 server_port:"+server_port,payment.getId());
        }


        return new CommonResult(500,"插入失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    //    System.out.println("from request:" + request.getHeader("code"));
        System.out.println("from parameter:" + headers);
        System.out.println(request.getHeader("X-Request-red"));
        System.out.println(request.getHeader("blue"));

        Payment payment = paymentSerivce.getPaymentById(id);
        if(payment != null){
            return new CommonResult<Payment>(200,"获取成功 server_port:"+server_port,payment);
        }
        return new CommonResult<Payment>(500,"获取失败");
    }
}
