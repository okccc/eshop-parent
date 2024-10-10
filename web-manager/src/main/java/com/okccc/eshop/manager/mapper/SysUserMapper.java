package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.dto.system.SysUserDto;
import com.okccc.eshop.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:23:17
 * @Desc:
 */
@Mapper
public interface SysUserMapper {

    // 根据用户名查找
    SysUser selectByUsername(String username);

    // 分页查询,带搜索条件,条件可能为空,所以用动态sql实现
    Page<SysUser> selectPage(SysUserDto sysUserDto);

    // 添加用户
    void insert(SysUser sysUser);

    // 修改用户
    void updateById(SysUser sysUser);

    // 根据id删除用户
    void deleteById(Long id);
}
