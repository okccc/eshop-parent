package com.okccc.eshop.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/22 28:27:19
 * @Desc:
 */
@Data
@AllArgsConstructor
@Schema(description = "验证码响应结果实体类")
public class CaptchaVo {

    @Schema(description = "验证码key")
    private String codeKey;

    @Schema(description = "验证码图片")
    private String codeImage;
}