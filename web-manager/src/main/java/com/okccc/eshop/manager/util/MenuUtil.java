package com.okccc.eshop.manager.util;

import com.okccc.eshop.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 16:51:15
 * @Desc:
 */
public class MenuUtil {

    /**
     * 构建树形菜单结构
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 封装最终数据的集合
        List<SysMenu> trees = new ArrayList<>();

        // 遍历所有菜单
        for (SysMenu sysMenu : sysMenuList) {
            // 递归入口：第一层菜单,即parentId=0
            if (sysMenu.getParentId() == 0) {
                // 根据第一层菜单找下层菜单,参数1是当前第一层菜单,参数2是所有菜单集合
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }

        // 返回树形菜单
        return trees;
    }

    /**
     * 递归查找下层菜单
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        // 封装下一层数据
        sysMenu.setChildren(new ArrayList<>());

        // 遍历所有菜单
        for (SysMenu menu : sysMenuList) {
            // 递归入口：sysMenu的id等于menu的parentId
            if (sysMenu.getId().longValue() == menu.getParentId().longValue()) {
                // 是下层数据,进行封装
                sysMenu.getChildren().add(findChildren(menu, sysMenuList));
            }
        }

        return sysMenu;
    }

}
