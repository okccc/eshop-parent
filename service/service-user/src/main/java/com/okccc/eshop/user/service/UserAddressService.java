package com.okccc.eshop.user.service;

import com.okccc.eshop.model.entity.user.UserAddress;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/12/12 15:32:32
 * @Desc:
 */
public interface UserAddressService {

    // 根据userId查询用户地址列表
    List<UserAddress> listByUserId(Long userId);

    // 根据id查询用户地址
    UserAddress getById(Long id);
}