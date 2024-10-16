package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.dto.system.SysRoleDto;
import com.okccc.eshop.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:41:07
 * @Desc:
 */
@Mapper
public interface SysRoleMapper {

    // 查询所有角色
    List<SysRole> selectList();

    // 分页查询,带搜索条件
    Page<SysRole> selectPage(SysRoleDto sysRoleDto);

    // 添加角色
    void insert(SysRole sysRole);

    // 修改角色
    void updateById(SysRole sysRole);

    // 根据id删除角色
    void deleteById(Long id);
}
