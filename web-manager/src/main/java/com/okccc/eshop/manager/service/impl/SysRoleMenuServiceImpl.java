package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.mapper.SysRoleMenuMapper;
import com.okccc.eshop.manager.service.SysMenuService;
import com.okccc.eshop.manager.service.SysRoleMenuService;
import com.okccc.eshop.model.dto.system.AssignMenuDto;
import com.okccc.eshop.model.entity.system.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/9/13 18:29:35
 * @Desc:
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> displayByRoleId(Long roleId) {
        // 查询系统中的所有菜单,这里同样要展示树形结构
        List<SysMenu> sysMenuList = sysMenuService.treeList();

        // 查询当前角色已分配的菜单
        log.info("角色菜单管理 - 根据角色id查询菜单：{}", roleId);
        List<Long> menuIdList = sysRoleMenuMapper.selectListByRoleId(roleId);

        // 封装返回数据
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("sysMenuList", sysMenuList);
        hashMap.put("menuIdList", menuIdList);
        return hashMap;
    }

    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        // 先删除角色之前分配的菜单
        Long roleId = assignMenuDto.getRoleId();
        log.info("角色菜单管理 - 根据角色id删除菜单：{}", roleId);
        sysRoleMenuMapper.deleteByRoleId(roleId);

        // 再给角色重新分配新的菜单
        List<Map<String, Number>> menuIdList = assignMenuDto.getMenuIdList();
        if (!CollectionUtils.isEmpty(menuIdList)) {
//            for (Map<String, Number> menuId : menuIdList) {
//                log.info("角色菜单管理 - 根据角色id分配菜单：{},{}", roleId, menuId);
//                sysRoleMenuMapper.insert(roleId, menuId);
//            }
            // 一次性批量插入比多次插入性能更好
            log.info("角色菜单管理 - 根据角色id分配菜单：{},{}", roleId, menuIdList);
            sysRoleMenuMapper.insertBatch(roleId, menuIdList);
        }
    }

}
