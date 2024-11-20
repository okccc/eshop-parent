package com.okccc.eshop.product.service;

import com.okccc.eshop.model.entity.product.ProductSku;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:31:21
 * @Desc:
 */
public interface ProductService {

    // 查询畅销商品列表
    List<ProductSku> getProductSkuBySale();
}
