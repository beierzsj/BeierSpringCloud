package com.beier.feign;

import com.beier.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.beier.feign.UserFeignClient.HystrixClientFallback;

/**
 *  用户Http请求的Feign 客户端。
 *
 * 注解FeignClient的传参：表示的是注册到 Eureka 服务上的模块名称，也就是需要访问的微服务名称。
 * @author hua
 * @create 2018/4/9.
 */
@FeignClient(name = "beier-provider" , fallback = HystrixClientFallback.class)//HystrixClientFallback为断路器在服务调用失败后走的方法集合类
public interface UserFeignClient {

    /**
     * 这里有两个坑需要注意：
     * 1、这里需要设置请求的方式为 RequestMapping 注解，用 GetMapping 注解是运行不成功的，即 GetMapping 不支持。
     * 2、注解 PathVariable 里面需要填充变量的名字，不然也是运行不成功的。
     * @param id
     * @return
     */
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id); // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value

    /**
     * 这里也有一个坑需要注意：
     *
     * 如果入参是一个对象的话，那么这个方法在 feign 里面默认为 POST 方法，就算你写成 GET 方式也无济于事。
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);

    //该请求不会成功
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestBody User user);

    /**
     * 这边采取了和Spring Cloud官方文档相同的做法，将fallback类作为内部类放入Feign的接口中，当然也可以单独写一个fallback类。
     */
    @Component
    class HystrixClientFallback implements UserFeignClient {
        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);
        /**
         * hystrix fallback方法
         * @param id id
         * @return 默认的用户
         */
        @Override
        public User findById(Long id) {
            return getDefaultUser(id);
        }

        @Override
        public User postUser(@RequestBody User user){
            return getDefaultUser(user.getId());
        }

        @Override
        public User getUser(@RequestBody User user){
            return getDefaultUser(user.getId());
        }

        private User getDefaultUser(Long id) {
            HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);

            /**设置错误后的默认值 start **/
            User user = new User();
            user.setId(-1L);
            user.setUsername("default username");
            user.setAge(0);
            /**设置错误后的默认值 end **/return user;
        }
    }
}
