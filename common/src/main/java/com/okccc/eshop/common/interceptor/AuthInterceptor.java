package com.okccc.eshop.common.interceptor;

import com.okccc.eshop.common.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: okccc
 * @Date: 2024/5/24 16:18:28
 * @Desc: 从网关过滤器传递的请求头中获取登录用户信息保存到ThreadLocal
 */
@ConditionalOnClass(value = DispatcherServlet.class)
public class AuthInterceptor implements HandlerInterceptor {

    static {
        System.out.println("我是登录校验拦截器,引用common模块的微服务启动时会加载(测试用)");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1.从网关传递的请求头中获取用户信息
        String userId = request.getHeader("userId");
        System.out.println("通用模块 - 用户信息拦截器：请求路径 = " + request.getRequestURI() + ", userId = " + userId);

        // 2.放到ThreadLocal,方便后续业务代码直接获取
        ThreadLocalUtil.setUser(Long.valueOf(userId));

        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 使用完释放线程中的变量
        ThreadLocalUtil.removeUser();
    }
}
