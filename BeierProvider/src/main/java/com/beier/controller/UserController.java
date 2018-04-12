package com.beier.controller;

import com.beier.model.User;
import com.beier.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务实例的相关内容
 * @author hua
 * @create 2018/4/9.
 */
@RestController
@RefreshScope  //这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新 ，即使用命令 curl  -X POST http://localhost:8000/refresh
@Api(description="用户接口")//swagger配置类描述
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiscoveryClient discoveryClient;// 服务发现客户端
    /**
     - DiscoveryClient 服务发现客户端，具有以下方法：
     - String description(); 获取描述
     - ServiceInstance getLocalServiceInstance(); @Deprecated 方法被删除，推荐不要使用。获取本地服务实例
     - List getInstances(String serviceId);  通过服务 ID，获取当前服务的服务实例
     - List getServices(); 获取所有服务 ID 列表
     */

    @GetMapping("/instance-info")
    @ApiOperation(value="服务器信息", notes="详细注释")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }

    @GetMapping("/simple/{id}")
    @ApiOperation(value="根据id查询用户信息", notes="详细注释")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    /**
     * 拿去自配置服务的配置，根据https://gitee.com/djying/BeierConfig 对应配置拿取
     * 刷新命令：curl  -X POST http://localhost:8000/refresh
     */
    @Value("${test}")
    private String test;

    @GetMapping("/test")
    @ApiOperation(value="测试配置服务", notes="详细注释")
    public String hello() {
        return this.test;
    }
}
