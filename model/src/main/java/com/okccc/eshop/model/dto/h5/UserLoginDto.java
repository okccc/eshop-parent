package com.okccc.eshop.model.dto.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:25:15
 * @Desc:
 */
@Data
@Schema(description = "用户登录请求参数实体类")
public class UserLoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;
}