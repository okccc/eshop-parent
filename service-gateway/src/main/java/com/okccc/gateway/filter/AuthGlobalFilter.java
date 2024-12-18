package com.okccc.gateway.filter;

import com.okccc.eshop.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/12/11 16:06:26
 * @Desc: 网关过滤器,参考org.springframework.cloud.gateway.filter.NettyRoutingFilter源码
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    static {
        System.out.println("我是网关过滤器,网关微服务启动时会加载(测试用)");
    }

    @Autowired
    private AntPathMatcher antPathMatcher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取HttpRequest对象
        ServerHttpRequest request = exchange.getRequest();

        // 2.判断当前路径是否需要拦截
        String path = request.getURI().getPath();
        // AntPathMatcher专门用来匹配"/*/"、"/**/"这些带通配符的路径
        if (!antPathMatcher.match("/api/**/auth/**", path)) {
            // 无需登录校验,直接放行
            return chain.filter(exchange);
        }

        // 3.从请求头获取token,请求头的key要和前端约定好
        String token = "";
        List<String> tokenList = request.getHeaders().get("token");
        if (tokenList != null) {
            token = tokenList.get(0);
        }

        // 4.解析token获取用户信息
        Long userId;
        try {
            Claims claims = JwtUtil.parseToken(token);
            userId = claims.get("userId", Long.class);
        } catch (Exception e) {
            // token失效,需要拦截
            ServerHttpResponse response = exchange.getResponse();
            // 返回未登录响应状态码,如果直接抛异常前端不知道具体原因
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        System.out.println("网关模块 - 登录校验过滤器：请求路径 = " + path + ", userId = " + userId);

        // 5.将用户信息传递给下游微服务
        ServerWebExchange swe = exchange
                // mutate就是对下游请求做更改
                .mutate()
                // 请求头的key要和下游微服务约定好
                .request(builder -> builder.header("userId", userId.toString()))
                .build();

        // 6.放行,传递给过滤器链的下一个过滤器
        return chain.filter(swe);
    }

    @Override
    public int getOrder() {
        // AuthGlobalFilter优先级要高于NettyRoutingFilter,可在各自filter方法打断点观察执行顺序
        return 0;
    }
}
