package com.beier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动服务api网关
 * 使用@EnableZuulProxy注解激活zuul。
 * 跟进该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置
 * @author hua
 * @create 2018/4/10.
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiZuulApp.class, args);
    }
}
