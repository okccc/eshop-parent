package com.okccc.eshop.cart;

import com.okccc.eshop.config.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: okccc
 * @Date: 2024/5/27 10:38:15
 * @Desc:
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.okccc.eshop.feign.product"}, defaultConfiguration = FeignConfig.class)
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