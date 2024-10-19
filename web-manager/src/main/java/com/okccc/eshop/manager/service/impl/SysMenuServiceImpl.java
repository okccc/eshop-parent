package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.mapper.SysMenuMapper;
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

}
