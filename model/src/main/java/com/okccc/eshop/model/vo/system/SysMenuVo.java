package com.okccc.eshop.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/6 19:13:17
 * @Desc: [{title: "会员管理", name: "users", children: [{title: "会员列表", name: "userInfo", children: null}]}...]
 */
@Schema(description = "系统菜单响应结果实体类")
@Data
public class SysMenuVo {

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "菜单路由")
    private String name;

    @Schema(description = "当前菜单的子菜单")
    private List<SysMenuVo> children;

}