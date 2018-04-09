package com.beier.Controller;

import com.beier.model.User;
import com.beier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务实例的相关内容
 * @author hua
 * @create 2018/4/9.
 */
@RestController
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
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }

    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

}
