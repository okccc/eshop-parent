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
 * @Desc: 购物车都是临时数据,为了提高读写性能,选择Redis的Hash结构存储
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

    @Operation(summary = "获取购物车列表")
    @GetMapping(value = "/auth/cartList")
    public Result<List<CartInfo>> getCartList() {
        List<CartInfo> list = cartService.list();
        return Result.ok(list);
    }

    @Operation(summary = "更新指定商品的选中状态(1选中,0取消)")
    @GetMapping(value = "/auth/checkCart/{skuId}/{isChecked}")
    public Result updateBySkuId(@PathVariable(value = "skuId") Long skuId, @PathVariable(value = "isChecked") Integer isChecked) {
        cartService.updateBySkuId(skuId, isChecked);
        return Result.ok();
    }

    @Operation(summary = "更新所有商品的选中状态(1选中,0取消)")
    @GetMapping(value = "/auth/allCheckCart/{isChecked}")
    public Result updateAll(@PathVariable(value = "isChecked") Integer isChecked){
        cartService.updateAll(isChecked);
        return Result.ok();
    }

    @Operation(summary = "删除指定商品")
    @DeleteMapping(value = "/auth/deleteCart/{skuId}")
    public Result removeBySkuId(@PathVariable("skuId") Long skuId) {
        cartService.removeBySkuId(skuId);
        return Result.ok();
    }

    @Operation(summary = "删除所有商品(清空购物车)")
    @GetMapping(value = "/auth/clearCart")
    public Result removeAll() {
        cartService.removeAll();
        return Result.ok();
    }

    @Operation(summary = "获取购物车选中的商品列表")
    @GetMapping(value = "/auth/getAllChecked")
    public List<CartInfo> getChecked() {
        // 远程调用：订单结算时需要获取购物车选中的商品列表,不是前端请求所以不需要返回Result
        return cartService.getChecked();
    }

    @Operation(summary = "删除购物车已生成订单的商品")
    @GetMapping(value = "/auth/deleteChecked")
    public void removeChecked() {
        // 远程调用：订单结算时需要删除已生成订单的购物车商品,不是前端请求所以不需要返回Result
        cartService.removeChecked();
    }

}
