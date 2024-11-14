package com.okccc.eshop.manager.handler;

import com.okccc.eshop.manager.result.ResultCodeEnum;
import lombok.Getter;

/**
 * @Author: okccc
 * @Date: 2024/4/22 17:59:32
 * @Desc:
 *
 * 问题：RuntimeException(String message)只能传message无法传code,这样给前端抛异常时响应格式就不统一
 * 解决：自定义异常类继承RuntimeException,并将ReturnCodeEnum作为构造参数传入,给前端统一响应Result格式
 */
@Getter
public class MyRuntimeException extends RuntimeException {

    private final Integer code;

    private final String message;

    private final ResultCodeEnum resultCodeEnum;

    public MyRuntimeException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

}
