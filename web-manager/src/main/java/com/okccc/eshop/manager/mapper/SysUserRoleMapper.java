package com.okccc.eshop.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    // 根据userId删除角色
    void deleteByUserId(Long userId);

    // 根据userId添加角色
    void insert(@Param("userId") Long userId, @Param("roleId") Long roleId);

    // 批量添加
    void insertBatch(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

}
