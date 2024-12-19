package com.okccc.eshop.common.config;

import com.okccc.eshop.common.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: okccc
 * @Date: 2024/6/20 14:51:43
 * @Desc: webmvc配置类
 *
 * 1.当前配置类所在包是com.okccc.eshop.common.config,与其它微服务主程序所在包路径不一致,是扫描不到的
 * 借鉴SpringBoot自动配置原理,可以将其添加到resources目录下的META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 *
 * 2.common模块会被网关模块引用,但是网关模块排除了SpringMVC相关依赖,启动时会被错
 * 借鉴SpringBoot配置类生效条件,添加@ConditionalOnClass(value = DispatcherServlet.class)注解
 */
@ConditionalOnClass(value = DispatcherServlet.class)
public class WebMvcConfig implements WebMvcConfigurer {

    // Could not autowire. No beans of 'AuthInterceptor' type found.
    // common模块是给别的微服务引用,本身并没有启动类,也就扫描不到这些组件
    // 方案1：既然没法自动装配那就new AuthInterceptor()手动创建吧
    // 方案2：借鉴SpringBoot自动配置原理,将其添加到META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/api/**/auth/**");
    }
}
