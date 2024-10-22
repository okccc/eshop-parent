package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.dto.system.AssignMenuDto;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/9/13 18:29:24
 * @Desc:
 */
public interface SysRoleMenuService {

    // 菜单数据回显
    Map<String, Object> displayByRoleId(Long roleId);

    // 分配菜单
    void doAssign(AssignMenuDto assignMenuDto);
}
