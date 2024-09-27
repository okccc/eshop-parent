package com.okccc.eshop.manager.controller.login;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.LoginService;
import com.okccc.eshop.manager.service.SysUserService;
import com.okccc.eshop.model.dto.system.LoginDto;
import com.okccc.eshop.model.entity.system.SysUser;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import com.okccc.eshop.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:16:55
 * @Desc: 登录接口包含获取验证码、登录、获取当前用户信息、登录校验拦截器四个功能
 *
 * 验证码作用：防止爬虫等自动化程序发送恶意攻击暴力破解密码
 *
 * 项目中使用的Token通常指JWT(JSON Web TOKEN),是一种轻量级的安全传输方式,用于身份验证和信息传递
 * F12 - Application - Storage - Cookies/Local storage是浏览器存储sessionId/token的地方
 */
@Tag(name = "登录接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "获取图片验证码")
    @GetMapping(value = "/getCaptcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo captchaVo = loginService.getCaptcha();
        return Result.ok(captchaVo);
    }

    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = loginService.login(loginDto);
        return Result.ok(loginVo);
    }

    @Operation(summary = "获取当前用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        // 根据token获取登录用户信息
        SysUser sysUser = sysUserService.getUserInfo(token);
        return Result.ok(sysUser);
    }

}
