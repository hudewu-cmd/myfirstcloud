package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentSerivce {
    public Long  create(Payment payment);
    public Payment getPaymentById(Long id);
}
