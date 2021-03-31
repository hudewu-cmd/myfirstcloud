package com.atguigu.springcloud.rule;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MySelfRule  implements IRule {
    private AtomicInteger index = new AtomicInteger();

    @Override
    public ServiceInstance choose(List<ServiceInstance> instances) {
        int size = instances.size();
        int i = incrmentAndGet(size);

        return instances.get(i);

    }

    private  int incrmentAndGet(int mod){
        System.out.println(mod);
        int cur = 0;
        int idx = 0;
        do {
             cur = index.get();
             idx = (cur+1)%mod;
        }while (!index.compareAndSet(cur,idx));
        System.out.println(idx);
        return idx;
    }
}
