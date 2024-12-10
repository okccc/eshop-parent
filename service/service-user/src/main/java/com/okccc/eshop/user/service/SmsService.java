package com.okccc.eshop.user.service;

/**
 * @Author: okccc
 * @Date: 2024/5/22 18:12:58
 * @Desc:
 */
public interface SmsService {

    // 发送验证码短信
    void sendSmsCode(String phone);
}
