package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.dto.system.LoginDto;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import com.okccc.eshop.model.vo.system.LoginVo;

/**
 * @Author: okccc
 * @Date: 2024/4/22 18:32:09
 * @Desc:
 */
public interface LoginService {

    // 获取图片验证码
    CaptchaVo getCaptcha();

    // 用户登录
    LoginVo login(LoginDto loginDto);
}
