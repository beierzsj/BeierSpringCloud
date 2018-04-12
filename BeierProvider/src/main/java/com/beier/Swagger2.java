package com.beier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置 (接口文档可视化ui)
 * 访问 http://localhost:服务端口/swagger-ui.html
 * @author hua
 * @create 2018/4/12.
 */
@Configuration
@ComponentScan(basePackages = { "com.beier.controller.*" })//配置controller路径
@EnableSwagger2 // 启用Swagger2
public class Swagger2  extends WebMvcConfigurerAdapter {//WebMvcConfigurerAdapter配置路径重定向（不继承这个类会报错）

    @Bean
    public Docket createRestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beier"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("beier接口文档")// API 标题
                .description("服务提供者接口")// API描述
                .contact(new Contact("beier","","940013154@qq.com"))// 联系人
                .version("1.0")// 版本号
                .build();
    }


    //Swagger配置路径重定向
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

}
