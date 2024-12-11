package com.okccc.eshop.user.mapper;

import com.okccc.eshop.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:32:19
 * @Desc:
 */
@Mapper
public interface UserInfoMapper {

    // 根据用户名查找
    UserInfo selectByUsername(String username);

    // 添加
    void insert(UserInfo userInfo);

}
