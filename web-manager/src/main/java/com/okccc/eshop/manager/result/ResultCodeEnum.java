package com.okccc.eshop.manager.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author: okccc
 * @Date: 2024/4/21 20:35:08
 * @Desc: 定义通用枚举类步骤：1.举值 - 2.构造 - 3.遍历
 *
 * 状态码,参考org.springframework.http.HttpStatus
 * 100 ~ 199：信息,服务器收到请求,需要请求者继续执行操作
 * 200 ~ 299：成功,操作被成功接收并处理
 * 300 ~ 399：重定向,需要进一步操作并完成请求
 * 400 ~ 499：客户端错误,请求包含语法错误或无法完成请求
 * 500 ~ 599：服务器错误,服务器在处理请求过程中发生了错误
 */
@Schema(description = "响应结果枚举类")
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(9999, "操作失败"),

    CAPTCHA_CODE_NOT_FOUND(301, "未输入验证码"),
    CAPTCHA_CODE_EXPIRED(302, "验证码已过期"),
    CAPTCHA_CODE_ERROR(303, "验证码错误"),
    ACCOUNT_NOT_EXIST_ERROR(304, "账号不存在"),
    ACCOUNT_ERROR(305, "用户名或密码错误"),
    ACCOUNT_DISABLED_ERROR(306, "该用户已被禁用"),
    LOGIN_AUTH(307, "用户未登录"),
    TOKEN_INVALID(308, "token失效"),
    ACCOUNT_EXIST_ERROR(309, "账号已存在"),

    NODE_ERROR( 401, "该节点下有子节点,不可以删除"),
    DATA_ERROR(402, "数据异常");

    @Schema(description = "业务状态码")
    private final Integer code;

    @Schema(description = "描述信息")
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 传统方式遍历
    public static ResultCodeEnum getReturnCodeEnum(Integer code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (Objects.equals(resultCodeEnum.getCode(), code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }

}
