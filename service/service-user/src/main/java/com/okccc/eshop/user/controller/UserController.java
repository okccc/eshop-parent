package com.okccc.eshop.user.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.dto.h5.UserRegisterDto;
import com.okccc.eshop.user.service.SmsService;
import com.okccc.eshop.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @Operation(summary = "发送短信验证码")
    @GetMapping(value = "/sms/sendCode/{phone}")
    public Result sendSmsCode(@PathVariable String phone) {
        smsService.sendSmsCode(phone);
        return Result.ok();
    }

    @Operation(summary = "注册")
    @PostMapping(value = "/userInfo/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return Result.ok();
    }

}
