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

    // 这里返回的是字符串,那么如何将图片转换成字符串呢？
    // base64算法可以将任意二进制文件(比如图片)编码成一个很长的字符串,如何验证呢？
    // swagger接口文档：调试结果的响应内容和对应的Base64Img
    // 前后端联调：F12找到响应data的codeImage,将其复制到html文件的img标签,用浏览器打开发现果然是个图片
    @Schema(description = "验证码图片")
    private String codeImage;
}