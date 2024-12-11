package com.okccc.eshop.model.dto.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:25:15
 * @Desc:
 */
@Data
@Schema(description="用户注册请求参数实体类")
public class UserRegisterDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机验证码")
    private String code;
}