package com.okccc.eshop.order;

import com.okccc.eshop.client.config.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: okccc
 * @Date: 2024/6/4 13:55:05
 * @Desc: 订单微服务需要远程调用购物车微服务、商品微服务、用户微服务
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.okccc.eshop.client"}, defaultConfiguration = FeignConfig.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}