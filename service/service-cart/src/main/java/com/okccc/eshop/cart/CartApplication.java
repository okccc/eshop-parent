package com.okccc.eshop.cart;

import com.okccc.eshop.client.config.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: okccc
 * @Date: 2024/5/27 10:38:15
 * @Desc: 购物车微服务需要远程调用商品微服务
 *
 * Error creating bean with name 'dataSource' defined in class path resource
 * [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]:
 * Failed to instantiate [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception with message: Cannot load driver class: com.mysql.cj.jdbc.Driver
 *
 * common模块在pom.xml引入了mybatis启动器,项目启动时就会自动去找mysql数据源的配置信息
 * 但是购物车功能全部使用redis实现,并没有在application.yml添加mysql相关配置,所以项目启动时就会报错
 * 在启动类注解添加exclude属性排除指定的自动配置类
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.okccc.eshop.client"}, defaultConfiguration = FeignConfig.class)
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

    // 注册RestTemplate组件
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}