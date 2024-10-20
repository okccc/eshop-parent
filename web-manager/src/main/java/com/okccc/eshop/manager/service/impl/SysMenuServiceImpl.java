package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.mapper.SysMenuMapper;
import com.okccc.eshop.manager.mapper.SysRoleMenuMapper;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.service.SysMenuService;
import com.okccc.eshop.manager.util.MenuUtil;
import com.okccc.eshop.model.entity.system.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 15:58:08
 * @Desc:
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> treeList() {
        // 先查询所有菜单
        log.info("菜单管理 - 查询所有菜单");
        List<SysMenu> sysMenuList = sysMenuMapper.selectList();
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }

        // 再将其封装成树形结构
        return MenuUtil.buildTree(sysMenuList);
    }

    @Override
    public void save(SysMenu sysMenu) {
        log.info("菜单管理 - 添加菜单：{}", sysMenu);
        sysMenuMapper.insert(sysMenu);
        // 问题：为角色分配菜单后比如系统管理全部选中,然后在系统管理下面添加新的子菜单,再点击分配菜单时发现新添加的子菜单默认也会选中
        // 解决：新添加子菜单后,要将其父菜单设置为半开状态,这样点击分配菜单时就不会被选中
        updateIsHalfByMenuId(sysMenu);
    }

    private void updateIsHalfByMenuId(SysMenu sysMenu) {
        // 获取当前添加菜单的父菜单
        log.info("菜单管理 - 根据parentId查询当前菜单的父菜单：{}", sysMenu.getParentId());
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());
        if (parentMenu != null) {
            // 将父菜单设置为半开状态
            log.info("角色菜单管理 - 将父菜单设置为半开状态：{}", parentMenu.getId());
            sysRoleMenuMapper.updateIsHalfByMenuId(parentMenu.getId());
            // 递归调用
            updateIsHalfByMenuId(parentMenu);
        }
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        log.info("菜单管理 - 根据id修改菜单：{}", sysMenu);
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        // 先查看当前菜单是否包含子菜单
        log.info("菜单管理 - 查询当前菜单是否包含子菜单：{}", id);
        int count = sysMenuMapper.selectCountByParentId(id);
        if (count > 0) {
            throw new MyRuntimeException(ResultCodeEnum.NODE_ERROR);
        }

        // 不包含就直接删除
        log.info("菜单管理 - 根据id删除菜单：{}", id);
        sysMenuMapper.deleteById(id);
    }

}
