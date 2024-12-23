package com.okccc.eshop.client.config;

import com.okccc.eshop.common.util.ThreadLocalUtil;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
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

    @Bean
    public RequestInterceptor userInfoInterceptor() {
        // 使用OpenFeign发起请求时在请求头携带用户信息
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 从ThreadLocal获取用户信息
                Long userId = ThreadLocalUtil.getUser();
                System.out.println("client模块 - Feign拦截器传递用户信息：userId = " + userId);

                // 通过请求头传递给下游微服务
                if (userId != null) {
                    template.header("userId", userId.toString());
                }
            }
        };
    }
}
