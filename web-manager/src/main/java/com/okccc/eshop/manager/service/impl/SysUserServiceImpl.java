package com.okccc.eshop.manager.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.mapper.SysUserMapper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.service.SysUserService;
import com.okccc.eshop.model.dto.system.SysUserDto;
import com.okccc.eshop.model.entity.system.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:22:48
 * @Desc:
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser getUserInfo(String token) {
        // 从redis查询当前登录用户信息
        String jsonStr = redisTemplate.opsForValue().get(token);
        return JSON.parseObject(jsonStr, SysUser.class);
    }

    @Override
    public void logout(String token) {
        // 将token从redis中删除
        redisTemplate.delete(token);
    }

    @Override
    public PageResult<SysUser> page(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询,带搜索条件
        log.info("用户管理 - 分页查询用户：{}", sysUserDto);
        Page<SysUser> page = sysUserMapper.selectPage(sysUserDto);

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public void save(SysUser sysUser) {
        // 先判断用户名是否存在
        log.info("用户管理 - 根据username查找用户：{}", sysUser.getUsername());
        SysUser dbSysUser = sysUserMapper.selectByUsername(sysUser.getUsername());
        if (dbSysUser != null) {
            throw new MyRuntimeException(ResultCodeEnum.ACCOUNT_EXIST_ERROR);
        }

        // 给密码加密
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));

        // 设置状态可用
        sysUser.setStatus(1);

        // 保存用户
        log.info("用户管理 - 添加用户：{}", sysUser);
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void updateById(SysUser sysUser) {
        // 给密码加密
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));

        // 修改用户
        log.info("用户管理 - 根据id修改用户：{}", sysUser);
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public void removeById(Long id) {
        log.info("用户管理 - 根据id删除用户：{}", id);
        sysUserMapper.deleteById(id);
    }

}
