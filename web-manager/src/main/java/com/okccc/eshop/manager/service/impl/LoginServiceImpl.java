package com.okccc.eshop.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.okccc.eshop.manager.constant.RedisConstant;
import com.okccc.eshop.manager.service.LoginService;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: okccc
 * @Date: 2024/4/22 18:32:39
 * @Desc:
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    // 自动注入时可以点击左侧Navigate按钮查看组件来源
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public CaptchaVo getCaptcha() {
        // 1.使用hutool工具类生成图片验证码(速度比easy-captcha快)
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(130, 48, 4, 2);
        // 获取验证码中的字符串
        String codeValue = captcha.getCode();
        // 将图片验证码转换成base64编码的字符串
        String codeImage = "data:image/png;base64," + captcha.getImageBase64();

        // 2.将验证码字符串存入redis,有效期1分钟
        // redis中key的命名规范通常是"项目名:功能名:uuid",为了方便统一管理将字符串常量放到Constant类
        // uuid作用：前端同一时刻可能有多个用户登录,此时redis会存储很多验证码,校验时就没法判断是哪个用户
        String codeKey = RedisConstant.CAPTCHA_PREFIX + UUID.randomUUID();
        // key过期会自动删除：a.客户端访问时发现过期于是删除(惰性删除) b.服务端定期检查发现过期立马删除
        // ttl查看生命周期：-1永不过期,-2已经过期
        redisTemplate.opsForValue().set(codeKey, codeValue, RedisConstant.CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        // 3.返回CaptchaVo对象
        return new CaptchaVo(codeKey, codeImage);
    }

}
