package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.mapper.SysRoleMapper;
import com.okccc.eshop.manager.mapper.SysUserRoleMapper;
import com.okccc.eshop.manager.service.SysUserRoleService;
import com.okccc.eshop.model.dto.system.AssignRoleDto;
import com.okccc.eshop.model.entity.system.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional  // 当方法中包含多个更新操作时要添加事务
    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {
        // 先删除用户之前分配的角色
        Long userId = assignRoleDto.getUserId();
        log.info("用户角色管理 - 根据用户id删除角色：{}", userId);
        sysUserRoleMapper.deleteByUserId(userId);

        // 模拟异常,测试事务
//        int i = 10/0;

        // 再给用户重新分配新的角色
        List<Long> roleIdList = assignRoleDto.getRoleIdList();
//        for (Long roleId : roleIdList) {
//            log.info("用户角色管理 - 根据用户id分配角色：{},{}", userId, roleId);
//            sysUserRoleMapper.insert(userId, roleId);
//        }
        // 一次性批量插入比多次插入性能更好
        log.info("用户角色管理 - 根据用户id批量插入角色：{},{}", userId, roleIdList);
        sysUserRoleMapper.insertBatch(userId, roleIdList);
    }

}
