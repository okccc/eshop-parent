package com.okccc.eshop.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:35:33
 * @Desc:
 */
@Data
@Schema(description = "角色搜索请求参数实体类")
public class SysRoleDto {

    @Schema(description = "角色名称")
    private String roleName;

}
