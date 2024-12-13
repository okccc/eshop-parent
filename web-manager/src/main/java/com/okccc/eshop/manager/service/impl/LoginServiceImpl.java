package com.okccc.eshop.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.okccc.eshop.manager.constant.RedisConstant;
import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.mapper.SysUserMapper;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.service.LoginService;
import com.okccc.eshop.manager.util.JwtUtil;
import com.okccc.eshop.model.dto.system.LoginDto;
import com.okccc.eshop.model.entity.system.SysUser;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import com.okccc.eshop.model.vo.system.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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

    @Autowired
    private SysUserMapper sysUserMapper;

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

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 1.校验验证码
        if (!StringUtils.hasText(loginDto.getCaptcha())) {
            // 未输入验证码
            throw new MyRuntimeException(ResultCodeEnum.CAPTCHA_CODE_NOT_FOUND);
        }

        String code = redisTemplate.opsForValue().get(loginDto.getCodeKey());
        if (code == null) {
            // 验证码已过期
            throw new MyRuntimeException(ResultCodeEnum.CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equalsIgnoreCase(loginDto.getCaptcha())) {
            // 验证码错误
            throw new MyRuntimeException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        // 2.校验用户名密码
        log.info("登录 - 根据用户名查找：{}", loginDto.getUsername());
        SysUser sysUser = sysUserMapper.selectByUsername(loginDto.getUsername());
        if (sysUser == null) {
            // 用户不存在
            throw new MyRuntimeException(ResultCodeEnum.ACCOUNT_NOT_EXIST_ERROR);
        }

        if (!sysUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            // 用户名或密码错误
            throw new MyRuntimeException(ResultCodeEnum.ACCOUNT_ERROR);
        }

        if (sysUser.getStatus() == 0) {
            // 该用户已被禁用
            throw new MyRuntimeException(ResultCodeEnum.ACCOUNT_DISABLED_ERROR);
        }

        // 3.登录成功生成token,前端将token存储在localStorage(5M),比cookie(4K)容量更大,后续发送请求时就会在请求头携带该token
        // 简洁版：直接拼接固定前缀加UUID作为token,保存到redis并设置有效期
//        String token = RedisConstant.LOGIN_PREFIX + UUID.randomUUID();
//        redisTemplate.opsForValue().set(token, sysUser.getId().toString(), RedisConstant.LOGIN_TTL_DAY, TimeUnit.DAYS);
        // 专业版：使用JWT生成token,登录校验时也是由JWT解析token,不需要存redis
        String token = JwtUtil.createToken(sysUser.getId());

        // 4.返回token
        return new LoginVo(token);
    }

}
