package com.okccc.eshop.order.controller;

import com.github.pagehelper.PageInfo;
import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.dto.h5.OrderInfoDto;
import com.okccc.eshop.model.entity.order.OrderInfo;
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

    @Operation(summary = "提交订单")
    @PostMapping("/auth/submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        // 提交订单需要干两件事：1.保存订单信息 2.保存订单明细信息
        Long orderId = orderService.submitOrder(orderInfoDto);
        return Result.ok(orderId);
    }

    @Operation(summary = "根据id查询订单")
    @GetMapping(value = "/auth/{id}")
    public Result<OrderInfo> getById(@PathVariable("id") Long id) {
        // 点击提交订单按钮会跳转到确认支付页面,需要根据id获取订单信息,展示订单支付信息
        OrderInfo orderInfo = orderService.getById(id);
        return Result.ok(orderInfo);
    }

    @Operation(summary = "立即购买")
    @GetMapping(value = "/auth/buy/{skuId}")
    public Result<TradeVo> buy(@PathVariable("skuId") Long skuId) {
        // 商品详情页可以直接下单,不经过购物车
        TradeVo tradeVo = orderService.buy(skuId);
        return Result.ok(tradeVo);
    }

    @Operation(summary = "查询所有订单")
    @GetMapping(value = "/auth/{pageNum}/{pageSize}")
    public Result<PageInfo<OrderInfo>> page(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, Integer orderStatus) {
        // 分页查询订单列表
        PageInfo<OrderInfo> pageResult = orderService.page(pageNum, pageSize, orderStatus);
        return Result.ok(pageResult);
    }
}
