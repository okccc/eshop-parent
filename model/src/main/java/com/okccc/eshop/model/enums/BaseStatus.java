package com.okccc.eshop.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @Author: okccc
 * @Date: 2024/9/9 10:37:42
 * @Desc:
 */
@Schema(description = "可用状态枚举类")
@Getter
public enum BaseStatus {

    ENABLE(0, "正常"),

    DISABLE(1, "禁用");

    private final Integer code;

    private final String name;

    BaseStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
