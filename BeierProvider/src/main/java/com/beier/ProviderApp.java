package com.beier;

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供者启动
 * @author hua
 * @create 2018/4/9.
 */
@SpringBootApplication
@EnableEurekaClient //注解@EnableEurekaClient上有@EnableDiscoveryClient注解
public class ProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class,args);
    }
}
