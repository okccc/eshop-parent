package com.okccc.eshop.manager.controller.login;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.LoginService;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:16:55
 * @Desc:
 *
 * 验证码作用：防止爬虫等自动化程序发送恶意攻击暴力破解密码
 */
@Tag(name = "登录接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "获取图片验证码")
    @GetMapping(value = "/getCaptcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo captchaVo = loginService.getCaptcha();
        return Result.ok(captchaVo);
    }

}
