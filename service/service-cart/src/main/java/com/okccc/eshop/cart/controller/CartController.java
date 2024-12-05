package com.okccc.eshop.cart.controller;

import com.okccc.eshop.cart.service.CartService;
import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.entity.h5.CartInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/27 15:53:23
 * @Desc:
 */
@Tag(name = "h5购物车接口")
@RestController
@RequestMapping(value = "/api/order/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "添加商品到购物车")
    @GetMapping(value = "/auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId, @PathVariable("skuNum") Integer skuNum) {
        cartService.save(skuId, skuNum);
        return Result.ok();
    }

    @Operation(summary = "查询购物车列表")
    @GetMapping(value = "/auth/cartList")
    public Result<List<CartInfo>> getCartList() {
        List<CartInfo> list = cartService.list();
        return Result.ok(list);
    }
}
