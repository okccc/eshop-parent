package com.okccc.eshop.user.service.impl;

import com.okccc.eshop.common.constant.RedisConstant;
import com.okccc.eshop.common.handler.MyRuntimeException;
import com.okccc.eshop.common.result.ResultCodeEnum;
import com.okccc.eshop.common.util.JwtUtil;
import com.okccc.eshop.model.dto.h5.UserLoginDto;
import com.okccc.eshop.model.dto.h5.UserRegisterDto;
import com.okccc.eshop.model.entity.user.UserInfo;
import com.okccc.eshop.user.mapper.UserInfoMapper;
import com.okccc.eshop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:31:53
 * @Desc:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 1.获取前端页面提交参数
        String username = userRegisterDto.getUsername();  // 注册用户名就是手机号
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        // 参数校验
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)
                || !StringUtils.hasText(nickName) || !StringUtils.hasText(code)) {
            throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
        }

        // 2.校验验证码
        // 根据key查询redis存储的验证码
        String redisCode = redisTemplate.opsForValue().get(username);

        // 对比输入的验证码和redis存储的验证码是否一致
        if (!code.equals(redisCode)) {
            // 验证码错误
            throw new MyRuntimeException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }

        // 3.校验注册用户
        log.info("用户微服务 - 根据用户名查找用户：{}", username);
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo != null) {
            // 用户名已存在
            throw new MyRuntimeException(ResultCodeEnum.USERNAME_IS_EXISTS);
        }

        // 用户不存在,就添加到数据库,此时userInfo=null所以要先创建对象
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setNickName(nickName);
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://127.0.0.1:9000/eshop-bucket/avatar.jpg");
        log.info("用户微服务 - 添加用户");
        userInfoMapper.insert(userInfo);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        // 1.获取前端页面提交参数
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        // 参数校验
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
        }

        // 2.校验登录用户
        log.info("用户微服务 - 根据用户名查找用户：{}", username);
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            // 用户不存在,抛异常
            throw new MyRuntimeException(ResultCodeEnum.USERNAME_IS_NOT_EXISTS);
        }

        // 用户存在,继续比对密码
        String inputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!inputPassword.equals(userInfo.getPassword())) {
            // 密码错误,抛异常
            throw new MyRuntimeException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 密码正确,继续检测用户是否被禁用
        if (userInfo.getStatus() == 0) {
            throw new MyRuntimeException(ResultCodeEnum.ACCOUNT_STOP);
        }

        // 3.登录成功生成token,前端将token存储在localStorage(5M),比cookie(4K)容量更大,后续发送请求时就会在请求头携带该token
        // 简洁版：直接拼接固定前缀加UUID作为token,保存到redis并设置有效期
//        String token = RedisConstant.APP_LOGIN_PREFIX + UUID.randomUUID();
//        redisTemplate.opsForValue().set(token, userInfo.getId().toString(), RedisConstant.APP_LOGIN_TTL_DAY, TimeUnit.DAYS);

        // 专业版：使用JWT生成token,登录校验时也是由JWT解析token,不需要存redis
        String token = JwtUtil.createToken(userInfo.getId());

        // 4.返回token
        return token;
    }

}
