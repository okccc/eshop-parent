package com.okccc.eshop.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: okccc
 * @Date: 2024/5/27 10:38:15
 * @Desc:
 */
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