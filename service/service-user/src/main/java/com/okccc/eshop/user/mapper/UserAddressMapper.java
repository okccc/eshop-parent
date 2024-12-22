package com.okccc.eshop.user.mapper;

import com.okccc.eshop.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/6/4 11:28:12
 * @Desc:
 */
@Mapper
public interface UserAddressMapper {

    // 根据userId查询用户地址列表
    List<UserAddress> selectListByUserId(Long userId);

    // 根据id查询用户地址
    UserAddress selectById(Long id);
}
