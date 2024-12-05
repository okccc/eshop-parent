package com.okccc.eshop.cart.service;

import com.okccc.eshop.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/27 15:54:15
 * @Desc:
 */
public interface CartService {

    // 添加商品到购物车
    void save(Long skuId, Integer skuNum);

    // 查询购物车商品列表
    List<CartInfo> list();
}
