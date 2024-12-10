package com.okccc.eshop.user.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.user.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:30:43
 * @Desc:
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private SmsService smsService;

    @Operation(summary = "发送短信验证码")
    @GetMapping(value = "/sms/sendCode/{phone}")
    public Result sendSmsCode(@PathVariable String phone) {
        smsService.sendSmsCode(phone);
        return Result.ok();
    }

}
