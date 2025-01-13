package com.okccc.eshop.manager.controller.system;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.SysRoleService;
import com.okccc.eshop.model.dto.system.SysRoleDto;
import com.okccc.eshop.model.entity.system.SysRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/4/25 10:57:14
 * @Desc:
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "分页查询角色")
    @GetMapping(value = "page")
    public Result<PageResult<SysRole>> page(Integer pageNum, Integer pageSize, SysRoleDto sysRoleDto) {
        // 分页查询,带搜索条件
        PageResult<SysRole> pageResult = sysRoleService.page(pageNum, pageSize, sysRoleDto);
        return Result.ok(pageResult);
    }

    @Operation(summary = "添加角色")
    @PostMapping
    public Result save(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @Operation(summary = "根据id修改角色")
    @PutMapping
    public Result updateById(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    @Operation(summary = "根据id删除角色")
    @DeleteMapping(value = "/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        sysRoleService.removeById(id);
        return Result.ok();
    }

}
