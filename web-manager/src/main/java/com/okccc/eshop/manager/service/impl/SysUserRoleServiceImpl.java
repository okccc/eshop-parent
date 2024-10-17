package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.mapper.SysRoleMapper;
import com.okccc.eshop.manager.mapper.SysUserRoleMapper;
import com.okccc.eshop.manager.service.SysUserRoleService;
import com.okccc.eshop.model.entity.system.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/9/13 18:29:03
 * @Desc:
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> displayByUserId(Long userId) {
        // 查询系统中的所有角色
        log.info("角色管理 - 查询系统中所有角色");
        List<SysRole> sysRoleList = sysRoleMapper.selectList();

        // 查询当前用户已分配的角色
        log.info("用户角色管理 - 根据用户id查询角色：{}", userId);
        List<Long> roleIdList = sysUserRoleMapper.selectListByUserId(userId);

        // 封装返回数据
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("sysRoleList", sysRoleList);
        hashMap.put("roleIdList", roleIdList);
        return hashMap;
    }

}
