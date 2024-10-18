package com.okccc.eshop.model.dto.system;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/3 10:14:58
 * @Desc: 前端输入参数格式 {userId: 19, roleIdList: [2, 5]}
 */
@Schema(description = "分配角色请求参数实体类")
@Data
public class AssignRoleDto {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id列表")
    @NotEmpty
    private List<Long> roleIdList;
}
