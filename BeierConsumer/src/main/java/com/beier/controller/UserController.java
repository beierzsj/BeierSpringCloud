package com.beier.controller;

import com.beier.feign.UserFeignClient;
import com.beier.model.User;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器通过 Feign 访问远端服务。
 * @author hua
 * @create 2018/4/9.
 */
@RestController
public class UserController {
    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${server.port}")
    private String port;//端口号
    public static Integer number = 1;//访问次数

    @GetMapping("/movie/{id}")
    public Map<String,Object> findById(@PathVariable Long id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("user",this.userFeignClient.findById(id));
        map.put("port",port);
        map.put("number",number);
        number++;
        return map;
    }

    @GetMapping("/test/post")
    public User testPost(User user) {
        return this.userFeignClient.postUser(user);
    }

    //该请求不会成功
    @GetMapping("/test/get")
    public User testGet(User user) {
        return this.userFeignClient.getUser(user);
    }
}
