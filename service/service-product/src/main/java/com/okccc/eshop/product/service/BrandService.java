package com.okccc.eshop.product.service;

import com.okccc.eshop.model.entity.product.Brand;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/21 18:53:47
 * @Desc:
 */
public interface BrandService {

    // 查询所有品牌
    List<Brand> list();
}
