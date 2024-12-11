package com.okccc.eshop.user.service;

import com.okccc.eshop.model.dto.h5.UserRegisterDto;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:31:43
 * @Desc:
 */
public interface UserService {

    // 注册
    void register(UserRegisterDto userRegisterDto);

}
