package com.okccc.eshop.admin.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/8/2 14:28:56
 * @Desc:
 *
 * RESTFul：Http协议的标准使用方案和风格,规定了如何设计请求路径、请求方式、参数传递
 *
 * 请求路径：url回归本质,统一资源定位符应该是名词,不能包含动词,通过请求方式来指定动作
 * 请求方式：没有请求体使用GET/DELETE(url资源唯一 ? 路径参数 : param参数),有请求体使用POST/PUT(json参数)
 * 参数传递：param参数、路径参数、json参数
 *
 * 操作类型    传统方式                  Rest风格(简洁优雅,以前crud要设计4个不同url,现在只要1个)
 * 查询操作    /user/getUser?id=1       GET /user/1
 * 保存操作    /user/saveUser           POST /user
 * 更新操作    /user/updateUser         PUT /user
 * 删除操作    /user/deleteUser?id=1    DELETE /user/1
 */
@Slf4j
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {

}
