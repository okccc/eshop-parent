package com.okccc.eshop.common.constant;

/**
 * @Author: okccc
 * @Date: 2024/8/3 11:02:00
 * @Desc: 存储在redis中的常量值
 */
public class RedisConstant {

    // 商品微服务：一级分类数据
    public static final String CATEGORY_ONE = "category:one";

    // 商品微服务：一级分类数据存活时间(天)
    public static final Integer CATEGORY_ONE_TTL = 7;

    // APP端：短信验证码存活时间(秒)
    public static final Integer APP_SMS_TTL_SEC = 60;

    // APP端：登录token前缀
    public static final String APP_LOGIN_PREFIX = "user:login:";

    // APP端：登录token存活时间(天)
    public static final Integer APP_LOGIN_TTL_DAY = 7;
}
