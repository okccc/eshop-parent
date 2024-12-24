package com.okccc.eshop.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

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
    VALIDATE_CODE_ERROR(201, "验证码错误"),
    USERNAME_IS_EXISTS(202, "该用户已存在"),
    USERNAME_IS_NOT_EXISTS(203, "该用户不存在"),
    LOGIN_ERROR(204, "用户名或密码错误"),
    LOGIN_AUTH(205, "用户未登录"),
    NODE_ERROR( 206, "该节点下有子节点,不可以删除"),
    DATA_ERROR(207, "数据异常"),
    ACCOUNT_STOP( 208, "账号已停用"),
    STOCK_LESS( 209, "库存不足"),

    // 后台系统登录
    ADMIN_ACCOUNT_EXIST_ERROR(301, "账号已存在"),
    ADMIN_CAPTCHA_CODE_NOT_FOUND(302, "未输入验证码"),
    ADMIN_CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
    ADMIN_CAPTCHA_CODE_ERROR(304, "验证码错误"),
    ADMIN_LOGIN_AUTH(305, "用户未登录"),
    ADMIN_ACCOUNT_NOT_EXIST_ERROR(306, "账号不存在"),
    ADMIN_ACCOUNT_ERROR(307, "用户名或密码错误"),
    ADMIN_ACCOUNT_DISABLED_ERROR(308, "该用户已被禁用"),
    ADMIN_ACCESS_FORBIDDEN(309, "无访问权限"),

    // APP端登录
    APP_LOGIN_AUTH(501, "用户未登录"),
    APP_LOGIN_PHONE_EMPTY(502, "手机号码为空"),
    APP_LOGIN_CODE_EMPTY(503, "验证码为空"),
    APP_SEND_SMS_TOO_OFTEN(504, "验证法发送过于频繁"),
    APP_LOGIN_CODE_EXPIRED(505, "验证码已过期"),
    APP_LOGIN_CODE_ERROR(506, "验证码错误"),
    APP_ACCOUNT_DISABLED_ERROR(507, "该用户已被禁用"),

    // TOKEN相关
    TOKEN_INVALID(601, "token无效");

    @Schema(description = "业务状态码")
    private final Integer code;

    @Schema(description = "描述信息")
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
