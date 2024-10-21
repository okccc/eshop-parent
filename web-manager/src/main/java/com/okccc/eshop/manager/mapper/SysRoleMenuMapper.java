package com.okccc.eshop.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 18:41:07
 * @Desc:
 */
@Mapper
public interface SysRoleMenuMapper {

    // 根据roleId查询菜单
    List<Long> selectListByRoleId(Long roleId);

    // 将父菜单设置为半开状态
    void updateIsHalfByMenuId(Long menuId);

}
