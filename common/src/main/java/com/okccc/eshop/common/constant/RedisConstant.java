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
}
