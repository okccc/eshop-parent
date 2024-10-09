package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.dto.system.SysUserDto;
import com.okccc.eshop.model.entity.system.SysUser;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:22:38
 * @Desc:
 */
public interface SysUserService {

    // 获取登录用户信息
    SysUser getUserInfo(String token);

    // 用户退出
    void logout(String token);

    // 分页查询,带搜索条件
    PageResult<SysUser> page(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

}
