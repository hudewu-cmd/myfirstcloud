package com.atguigu.springcloud.rule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IRule {

    ServiceInstance choose(List<ServiceInstance> instances);
}
