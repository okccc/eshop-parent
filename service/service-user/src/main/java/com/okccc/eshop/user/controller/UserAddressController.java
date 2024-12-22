package com.okccc.eshop.user.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.common.util.ThreadLocalUtil;
import com.okccc.eshop.model.entity.user.UserAddress;
import com.okccc.eshop.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/12/12 15:31:28
 * @Desc:
 */
@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value = "/api/user/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Operation(summary = "根据userId查询用户地址列表")
    @GetMapping(value = "/auth/getUserAddressList")
    public Result<List<UserAddress>> getUserAddressList() {
        // 远程调用：订单模块确认订单时需要获取用户地址列表,不是前端请求所以不需要返回Result
        Long userId = ThreadLocalUtil.getUser();
        List<UserAddress> list = userAddressService.listByUserId(userId);
        return Result.ok(list);
    }

    @Operation(summary = "根据id查询地址")
    @GetMapping(value = "/getUserAddress/{id}")
    public UserAddress getUserAddressById(@PathVariable("id") Long id) {
        // 远程调用：订单模块确认订单时需要获取用户地址,不是前端请求所以不需要返回Result
        return userAddressService.getById(id);
    }
}
