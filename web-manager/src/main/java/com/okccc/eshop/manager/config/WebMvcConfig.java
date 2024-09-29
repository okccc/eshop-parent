package com.okccc.eshop.manager.config;

import com.okccc.eshop.manager.interceptor.AuthInterceptor;
import com.okccc.eshop.manager.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: okccc
 * @Date: 2024/4/22 16:58:39
 * @Desc: WebMvcConfigurer接口提供了配置SpringMVC各种组件的方法,直接模糊拼接方法名搜索
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthProperties authProperties;

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

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                // 登录校验拦截器
                .addInterceptor(authInterceptor)
                // 添加拦截路径,可以是单个路径也可以是通配符路径,/*表示单层路径,/**表示所有路径
                .addPathPatterns("/**")
                // 排除拦截路径,登录功能和验证码功能不需要做登录校验,路径可以放到配置文件方便维护
                .excludePathPatterns(authProperties.getNoAuthUrls());
    }

}
