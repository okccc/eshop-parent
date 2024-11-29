package com.okccc.eshop.cart.service;

/**
 * @Author: okccc
 * @Date: 2024/5/27 15:54:15
 * @Desc:
 */
public interface CartService {

    // 添加商品到购物车
    void save(Long skuId, Integer skuNum);
}
