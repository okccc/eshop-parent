package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 16:28:09
 * @Desc:
 */
@Mapper
public interface SysMenuMapper {

    // 查询所有菜单
    List<SysMenu> selectList();

    // 添加菜单
    void insert(SysMenu sysMenu);

    // 根据parentId查询当前菜单的父菜单
    SysMenu selectById(Long parentId);

    // 修改菜单
    void updateById(SysMenu sysMenu);

    // 根据id查询当前菜单是否包含子菜单
    int selectCountByParentId(Long id);

    // 根据id删除菜单
    void deleteById(Long id);

    // 根据userId查询菜单
    List<SysMenu> selectListByUserId(Long userId);

}
