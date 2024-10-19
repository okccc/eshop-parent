package com.okccc.eshop.manager.controller.system;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.SysMenuService;
import com.okccc.eshop.model.entity.system.SysMenu;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 15:57:14
 * @Desc: 树形菜单前端使用Element Plus实现,官网地址 https://element-plus.org/zh-CN/component/table.html
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "查询菜单列表")
    @GetMapping(value = "/treeList")
    public Result<List<SysMenu>> treeList() {
        // 菜单是层级结构,通过id和parent_id绑定关系,只有两层数据,可以一次性加载,并将其封装成前端需要的树形结构
        List<SysMenu> list = sysMenuService.treeList();
        return Result.ok(list);
    }

}
