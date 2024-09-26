package com.okccc.eshop.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc:
 */
@Schema(description = "登录请求参数实体类")
@Data
public class LoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户输入的验证码")
    private String captcha;

    @Schema(description = "验证码key")
    private String codeKey;

}
