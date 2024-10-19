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

}
