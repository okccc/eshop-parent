package com.okccc.eshop.manager.controller.system;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.SysUserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
