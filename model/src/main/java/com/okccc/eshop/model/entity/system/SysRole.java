package com.okccc.eshop.model.entity.system;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:35:33
 * @Desc:
 */
@Schema(description = "系统角色实体类")
@Data
public class SysRole extends BaseEntity {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "描述")
    private String description;

}
