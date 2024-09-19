package com.okccc.eshop.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: okccc
 * @Date: 2024/4/22 16:58:39
 * @Desc: WebMvcConfigurer接口提供了配置SpringMVC各种组件的方法,直接模糊拼接方法名搜索
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 处理跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 添加路径规则
                .allowedOriginPatterns("*")   // 允许请求来源的域规则
                .allowedMethods("*")          // 允许的请求方法
                .allowedHeaders("*")          // 允许的请求头
                .allowCredentials(true);      // 是否允许在跨域的情况下传递Cookie
    }

}
