package com.okccc.eshop.manager.constant;

/**
 * @Author: okccc
 * @Date: 2024/8/3 11:02:00
 * @Desc: 存储在redis中的常量值
 */
public class RedisConstant {

    // 后台管理系统：图片验证码前缀
    public static final String CAPTCHA_PREFIX = "manager:captcha:";

    // 后台管理系统：图片验证码存活时间(秒)
    public static final Integer CAPTCHA_TTL_SEC = 60;

    // 后台管理系统：登录token前缀
    public static final String LOGIN_PREFIX = "manager:login:";

    // 后台管理系统：登录token存活时间(天)
    public static final Integer LOGIN_TTL_DAY = 7;
}
