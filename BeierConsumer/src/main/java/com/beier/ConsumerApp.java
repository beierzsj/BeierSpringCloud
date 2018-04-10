package com.beier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ribbon.RibbonConfig;

/**
 * 微服务接入 Feign 进行客户端负载均衡，通过 FeignClient 调用远程 Http 微服务，若访问出错或者出现雪崩效应，则会断路器跳转到fallback方法内
 * @author hua
 * @create 2018/4/9.
 */
@SpringBootApplication
@RibbonClient(name = "beier-provider", configuration = RibbonConfig.class)//负载均衡自定义配置ribbonConfig
@EnableFeignClients //开启客户端feign
@EnableCircuitBreaker //开启断路器功能
@EnableEurekaClient
public class ConsumerApp {
    /**
     * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
     * @return restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
    }

}
