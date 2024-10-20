package com.okccc.eshop.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/6 18:41:07
 * @Desc:
 */
@Mapper
public interface SysRoleMenuMapper {

    // 将父菜单设置为半开状态
    void updateIsHalfByMenuId(Long menuId);

}
