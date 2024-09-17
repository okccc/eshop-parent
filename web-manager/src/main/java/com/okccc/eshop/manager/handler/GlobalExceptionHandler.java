package com.okccc.eshop.manager.handler;

import com.okccc.eshop.manager.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: okccc
 * @Date: 2024/4/22 17:06:12
 * @Desc: 统一处理异常(自动挡)而不是给前端响应Whitelabel Error Page,不用再写try/catch(手动挡)
 *
 * 统一处理异常之前：
 * {"status": 500, "error": "Internal Server Error", "path": "/admin/system/login", "timestamp": "2024-04-24T02:33:11"}
 *
 * 统一处理异常之后：
 * {"code":202, "message":"验证码错误", "data":null, "timestamp":1713927412809}
 */
//@ControllerAdvice  // Controller增强器,集中处理所有Controller发生的错误
//@ResponseBody  // 像Controller一样给前端返回JSON数据
@RestControllerAdvice  // 点进去发现就等于 @ControllerAdvice + @ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获所有异常,匹配不到具体异常时才会走这里(兜底)
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> error(Exception e) {
        // 记录异常信息
        log.error(e.getMessage(), e);
        // 给前端响应结果
        return Result.fail();
    }

    /**
     * 捕获指定异常,优先级更高
     */
    @ExceptionHandler(value = MyRuntimeException.class)
    public Result<String> error(MyRuntimeException e) {
        // 记录异常信息,e是完整的错误日志,e.getMessage()是":"后面那一小截
        log.error(e.getMessage(), e);
        // 给前端响应结果
        return Result.fail(e.getResultCodeEnum());
    }

}
