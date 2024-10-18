package com.okccc.eshop.manager.controller.system;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.SysUserRoleService;
import com.okccc.eshop.model.dto.system.AssignRoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/9/14 15:36:26
 * @Desc:
 */
@Tag(name = "用户角色接口")
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Operation(summary = "角色数据回显")
    @GetMapping(value = "/displayByUserId/{userId}")
    public Result<Map<String, Object>> displayByUserId(@PathVariable("userId") Long userId) {
        // 当点击分配角色按钮时,需要展示两部分数据：1.系统中的所有角色 2.当前用户已分配的角色(选中状态)
        Map<String, Object> map = sysUserRoleService.displayByUserId(userId);
        return Result.ok(map);
    }

    @Operation(summary = "分配角色")
    @PostMapping(value = "/doAssign")
    public Result doAssign(@RequestBody AssignRoleDto assignRoleDto) {
        // 分配角色分两步：先删除该用户之前分配的角色,再给用户重新分配新的角色
        sysUserRoleService.doAssign(assignRoleDto);
        return Result.ok();
    }

}
