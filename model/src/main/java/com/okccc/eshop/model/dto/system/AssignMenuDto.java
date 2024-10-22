package com.okccc.eshop.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/5/6 19:13:17
 * @Desc:
 */
@Schema(description = "分配菜单请求参数实体类")
@Data
public class AssignMenuDto {

    @Schema(description = "角色id")
    private Long roleId;

    // 前端输入参数格式 {roleId: 2, menuIdList: [{menuId: 1, isHalf: 0}, {menuId: 2, isHalf: 0}]}
    // Map集合的key肯定是String,这里value包含Long(menuId)、Integer(isHalf)等多种数值类型,所以使用它们的父类Number
    @Schema(description = "菜单id列表")
    private List<Map<String, Number>> menuIdList;
}