package com.okccc.eshop.order.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.vo.h5.TradeVo;
import com.okccc.eshop.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/6/4 14:06:51
 * @Desc:
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping(value="/api/order/orderInfo")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "创建订单")
    @GetMapping(value = "/auth/createOrder")
    public Result<TradeVo> createOrder() {
        // 点击去结算按钮会跳转到创建订单页面,需要干两件事：1.获取用户地址列表选中默认地址 2.获取购物车选中商品列表并计算总金额
        TradeVo tradeVo = orderService.createOrder();
        return Result.ok(tradeVo);
    }
}
