package com.okccc.eshop.manager.controller.login;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.LoginService;
import com.okccc.eshop.manager.service.SysMenuService;
import com.okccc.eshop.manager.service.SysUserService;
import com.okccc.eshop.manager.util.ThreadLocalUtil;
import com.okccc.eshop.model.dto.system.LoginDto;
import com.okccc.eshop.model.vo.system.CaptchaVo;
import com.okccc.eshop.model.vo.system.LoginVo;
import com.okccc.eshop.model.vo.system.SysMenuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:16:55
 * @Desc: 登录接口包含获取验证码、登录、获取当前用户信息、登录校验拦截器四个功能
 *
 * 验证码作用：防止爬虫等自动化程序发送恶意攻击暴力破解密码
 *
 * 项目中使用的Token通常指JWT(JSON Web TOKEN),是一种轻量级的安全传输方式,用于身份验证和信息传递
 * F12 - Application - Storage - Cookies/Local storage是浏览器存储sessionId/token的地方
 *
 * JWT是由header(头部)、payload(负载)、signature(签名)三部分组成的字符串,中间以"."分隔
 * Header：由JSON对象{"typ":"JWT","alg":"HS256"}经过base64url编码得到的字符串,保存JWT的类型和签名算法等元信息
 * Payload：也叫Claims,也是JSON对象经过base64url编码得到的,保存要传递的具体信息,比如自定义字段userId和过期时间exp
 * Signature：由头部、负载和密钥三部分经过指定的签名算法计算得到的一个字符串,任一部分变动都会导致最终结果发生变化
 *
 * 服务端验证：根据客户端携带token的第一部分、第二部分加上密钥重新计算签名,和第三部分的原有签名对比,结果一致说明token没被篡改
 *
 * 为什么要经过base64url编码?
 * jwt可以通过在url后面拼接字符串的方式传递,字符串不能包含特殊符号,要想在url中安全传递json字符串,就必须对其进行url编码
 */
@Tag(name = "登录接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

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
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
//        // 根据token获取登录用户信息
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        return Result.ok(sysUser);
//    }
    public Result<Long> getUserInfo() {
        // 优化：登录校验时已经根据token获取登录用户信息,直接从ThreadLocal获取即可
        Long userId = ThreadLocalUtil.getUser();
        return Result.ok(userId);
    }

    @Operation(summary = "退出")
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        // logout是在前端实现的,前端会清空本地存储的token并跳转到login页面
        sysUserService.logout(token);
        return Result.ok();
    }

    @Operation(summary = "动态菜单")
    @GetMapping(value = "/menus")
    public Result<List<SysMenuVo>> menus() {
        // 动态菜单：首页的左侧菜单不是固定的,应该根据当前登录用户所对应的角色动态获取
        Long userId = ThreadLocalUtil.getUser();

        // 查询当前用户可以操作的菜单(多表关联)
        List<SysMenuVo> list = sysMenuService.treeListByUserId(userId);
        return Result.ok(list);
    }

}
