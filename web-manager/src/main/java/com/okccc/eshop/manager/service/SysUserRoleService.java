package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.dto.system.AssignRoleDto;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/9/13 18:28:49
 * @Desc:
 */
public interface SysUserRoleService {

    // 根据userId回显角色数据
    Map<String, Object> displayByUserId(Long userId);

    // 分配角色
    void doAssign(AssignRoleDto assignRoleDto);
}
