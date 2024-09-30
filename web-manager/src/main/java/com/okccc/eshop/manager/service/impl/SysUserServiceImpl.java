package com.okccc.eshop.manager.service.impl;

import com.alibaba.fastjson2.JSON;
import com.okccc.eshop.manager.service.SysUserService;
import com.okccc.eshop.model.entity.system.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:22:48
 * @Desc:
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser getUserInfo(String token) {
        // 从redis查询当前登录用户信息
        String jsonStr = redisTemplate.opsForValue().get(token);
        return JSON.parseObject(jsonStr, SysUser.class);
    }

    @Override
    public void logout(String token) {
        // 将token从redis中删除
        redisTemplate.delete(token);
    }

}
