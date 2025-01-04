package com.okccc.eshop.pay;

import com.okccc.eshop.client.config.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: okccc
 * @Date: 2024/7/12 16:55:50
 * @Desc: 支付微服务需要远程调用订单微服务、商品微服务
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.okccc.eshop.client", defaultConfiguration = FeignConfig.class)
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}