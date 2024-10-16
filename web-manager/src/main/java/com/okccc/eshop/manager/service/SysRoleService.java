package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.dto.system.SysRoleDto;
import com.okccc.eshop.model.entity.system.SysRole;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:40:07
 * @Desc:
 */
public interface SysRoleService {

    // 分页查询,带搜索条件
    PageResult<SysRole> page(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto);

    // 添加角色
    void save(SysRole sysRole);

    // 修改角色
    void updateById(SysRole sysRole);

    // 删除角色
    void removeById(Long id);

}
