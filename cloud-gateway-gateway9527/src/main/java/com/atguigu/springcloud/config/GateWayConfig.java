package com.atguigu.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path",r -> r.path("/guonei").uri("https://news.baidu.com/"))
                .build();
    }
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path2",r -> r.path("/guoji").uri("https://news.baidu.com/"))
                .build();
    }
}
