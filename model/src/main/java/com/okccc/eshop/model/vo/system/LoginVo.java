package com.okccc.eshop.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc:
 */
@Schema(description = "登录响应结果实体类")
@Data
@AllArgsConstructor
public class LoginVo {

    @Schema(description = "令牌")
    private String token;

}
