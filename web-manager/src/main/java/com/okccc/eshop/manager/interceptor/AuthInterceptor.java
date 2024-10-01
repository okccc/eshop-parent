package com.okccc.eshop.manager.interceptor;

import com.alibaba.fastjson2.JSON;
import com.okccc.eshop.manager.constant.RedisConstant;
import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.util.ThreadLocalUtil;
import com.okccc.eshop.model.entity.system.SysUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: okccc
 * @Date: 2024/4/24 14:34:18
 * @Desc: 除了登录和获取验证码不需要做登录校验,其它后端接口必须在用户登录成功以后才能访问
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 预检请求用来检查是否支持跨域,直接放行
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 从请求头获取token
        String token = request.getHeader("token");

        // token为空说明用户未登录
        if (!StringUtils.hasText(token)) {
            throw new MyRuntimeException(ResultCodeEnum.LOGIN_AUTH);
        }

        // 继续查询redis,不存在说明token已失效
        String value = redisTemplate.opsForValue().get(token);
        if (!StringUtils.hasText(value)) {
            throw new MyRuntimeException(ResultCodeEnum.TOKEN_INVALID);
        }

        // 查到了就将用户信息存储到ThreadLocal,方便后续直接获取
        SysUser sysUser = JSON.parseObject(value, SysUser.class);
        ThreadLocalUtil.setSysUser(sysUser);

        // 临界情况：假设token有效时间30分钟,在29:50拿到用户数据,但是很快token就会失效
        // 解决方法：重置token过期时间,维持用户的登录状态,保证当前线程内的所有请求都能执行完
        redisTemplate.expire(token, RedisConstant.LOGIN_TTL_DAY, TimeUnit.DAYS);

        // 登录校验完成,放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // SpringMVC使用了线程池,线程使用完不会销毁而是放回到线程池,等待处理下一个请求
        // 所以处理完请求要清理线程中的用户信息,不然处理下一个请求时保存的用户信息就不对了
        ThreadLocalUtil.removeSysUser();
    }

    /**
     * 被拦截之后走不到Controller,为了保持数据格式统一,需要手动给前端响应JSON
     */
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}