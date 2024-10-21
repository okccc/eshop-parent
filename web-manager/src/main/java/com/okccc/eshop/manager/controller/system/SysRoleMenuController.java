package com.okccc.eshop.manager.controller.system;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.SysRoleMenuService;
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
 * @Date: 2024/9/14 15:31:21
 * @Desc:
 */
@Tag(name = "角色菜单接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "菜单数据回显")
    @GetMapping(value = "/displayByRoleId/{roleId}")
    public Result<Map<String, Object>> displayByRoleId(@PathVariable("roleId") Long roleId) {
        // 当点击分配菜单按钮时,需要展示两部分数据：1.系统中的所有菜单 2.当前角色已分配的菜单(选中状态)
        Map<String, Object> map = sysRoleMenuService.displayByRoleId(roleId) ;
        return Result.ok(map);
    }

}
