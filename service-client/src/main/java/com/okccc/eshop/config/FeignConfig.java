package com.okccc.eshop.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Author: okccc
 * @Date: 2024/6/15 22:21:46
 * @Desc: Feign配置类
 */
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        // FeignClient日志级别：NONE/BASIC/HEADERS/FULL
        return Logger.Level.FULL;
    }
}
