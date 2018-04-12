package com.beier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 通过@EnableConfigServer注解激活配置服务.
 * 说明：
 * 在application.yml中有个git.uri的配置，目前配置的是https://github.com/eacdy/spring-cloud-study/
 * 获取git上的资源信息遵循如下规则：
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 *
 * 例如本例的服务提供者：可使用以下路径来访问beier-provider-dev.properties：
 * http://localhost:8040/beier-provider-dev.properties
 * http://localhost:8040/beier-provider/dev
 * ...
 * @author hua
 * @create 2018/4/10.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
