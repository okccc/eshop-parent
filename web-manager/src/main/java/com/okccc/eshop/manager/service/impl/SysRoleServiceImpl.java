package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.mapper.SysRoleMapper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.service.SysRoleService;
import com.okccc.eshop.model.dto.system.SysRoleDto;
import com.okccc.eshop.model.entity.system.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:40:18
 * @Desc:
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageResult<SysRole> page(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询,带搜索条件
        log.info("角色管理 - 分页查询角色：{}", sysRoleDto);
        Page<SysRole> page = sysRoleMapper.selectPage(sysRoleDto);

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public void save(SysRole sysRole) {
        log.info("角色管理 - 添加角色：{}", sysRole);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void updateById(SysRole sysRole) {
        log.info("角色管理 - 根据id修改角色：{}", sysRole);
        sysRoleMapper.updateById(sysRole);
    }

    @Override
    public void removeById(Long id) {
        log.info("角色管理 - 根据id删除角色：{}", id);
        sysRoleMapper.deleteById(id);
    }

}
