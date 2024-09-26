package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:23:17
 * @Desc:
 */
@Mapper
public interface SysUserMapper {

    // 根据用户名查找
    SysUser selectByUsername(String username);
}
