package com.okccc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

/**
 * @Author: okccc
 * @Date: 2024/5/25 10:30:15
 * @Desc:
 *
 * Description:
 * Spring MVC found on classpath, which is incompatible with Spring Cloud Gateway.
 * Action:
 * Please set spring.main.web-application-type=reactive or remove spring-boot-starter-web dependency.
 * 分析：Spring Cloud Gateway是基于WebFlux的响应式编程,Spring MVC是基于Servlet的阻塞式编程,两者不兼容
 * 解决：网关模块引入common模块时将spring-webmvc依赖排除
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // 注册AntPathMatcher组件
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}