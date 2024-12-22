package com.okccc.eshop.user.service.impl;

import com.okccc.eshop.model.entity.user.UserAddress;
import com.okccc.eshop.user.mapper.UserAddressMapper;
import com.okccc.eshop.user.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/12/12 15:32:55
 * @Desc:
 */
@Slf4j
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> listByUserId(Long userId) {
        log.info("用户微服务 - 根据userId查询用户地址列表：{}", userId);
        return userAddressMapper.selectListByUserId(userId);
    }

    @Override
    public UserAddress getById(Long id) {
        log.info("用户微服务 - 根据id查询用户地址：{}", id);
        return userAddressMapper.selectById(id);
    }
}
