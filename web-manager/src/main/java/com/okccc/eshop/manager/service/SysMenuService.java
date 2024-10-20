package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.entity.system.SysMenu;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 15:57:54
 * @Desc:
 */
public interface SysMenuService {

    // 查询所有菜单
    List<SysMenu> treeList();

    // 添加菜单
    void save(SysMenu sysMenu);

    // 修改菜单
    void updateById(SysMenu sysMenu);

    // 删除菜单
    void removeById(Long id);
}
