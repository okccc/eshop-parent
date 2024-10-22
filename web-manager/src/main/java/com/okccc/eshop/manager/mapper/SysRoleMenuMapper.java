package com.okccc.eshop.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/5/6 18:41:07
 * @Desc:
 */
@Mapper
public interface SysRoleMenuMapper {

    // 根据roleId查询菜单
    List<Long> selectListByRoleId(Long roleId);

    // 根据roleId删除菜单
    void deleteByRoleId(Long roleId);

    // 根据roleId添加菜单
    void insert(Long roleId, Map<String, Number> menuId);

    // 批量添加
    void insertBatch(Long roleId, List<Map<String, Number>> menuIdList);

    // 将父菜单设置为半开状态
    void updateIsHalfByMenuId(Long menuId);

}
