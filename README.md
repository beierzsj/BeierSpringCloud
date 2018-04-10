# 项目简介

Spring Cloud章节的配套代码，如有疑问，请移步至该地址。

内容主要包含：

| 微服务角色                 | 对应的技术选型                              |
| --------------------- | ------------------------------------ |
| 注册中心(Register Server) | Eureka                               |
| 服务提供者                 | spring mvc、spring-data-jpa、h2等       |
| 服务消费者                 | Ribbon/Feign消费服务提供者的接口               |
| 熔断器                   | Hystrix，包括Hystrix Dashboard以及Turbine |
| 配置服务                  | Spring Cloud Config Server           |
| API Gateway           | Zuul                                 |



# 准备

## 环境准备：

| 工具    | 版本或描述                |
| ----- | -------------------- |
| JDK   | 1.8                  |
| IDE   | STS 或者 IntelliJ IDEA |
| Maven | 3.x                  |

## 主机名配置：

| 主机名配置（C:\Windows\System32\drivers\etc\hosts文件） |
| ---------------------------------------- |
| 127.0.0.1 discovery config-server gateway movie user feign ribbon |

## 主机规划：

| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| beier-eureka            | 8761 | 注册中心（同时也是配置服务客户端）                   | /               |
| beier-provider               | 8000 | 服务提供者                  | /              |
| beier-consumer       | 8011 | 服务消费者(整合fegin + ribbon + hystrix)             | /        |
| beier-api-zuul                 | 8050 | 服务api网关            | /           |
| beier-config-client               | 8041 | 配置服务的客户端               | /            |
| beier-config-server               | 8040 | 配置服务                   | /            |
| beier-hystrix-dashboard           | 8030 | hystrix监控              | /hystrix.stream |
| beier-hystrix-turbine             | 8031 | turbine                | /turbine.stream |


