package com.okccc.eshop.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/3 10:55:40
 * @Desc:
 */
@Mapper
public interface SysUserRoleMapper {

    // 根据userId查询角色
    List<Long> selectListByUserId(Long userId);

}
