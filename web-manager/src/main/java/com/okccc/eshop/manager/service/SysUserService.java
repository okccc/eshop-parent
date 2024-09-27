package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.entity.system.SysUser;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:22:38
 * @Desc:
 */
public interface SysUserService {

    // 获取登录用户信息
    SysUser getUserInfo(String token);

}
