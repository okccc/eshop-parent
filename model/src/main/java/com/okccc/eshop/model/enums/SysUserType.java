package com.okccc.eshop.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @Author: okccc
 * @Date: 2024/9/9 10:35:04
 * @Desc:
 */
@Schema(description = "系统用户类型枚举类")
@Getter
public enum SysUserType {

    ADMIN(0, "管理员"),

    COMMON(1, "普通用户");

    private final Integer code;

    private final String name;

    SysUserType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
