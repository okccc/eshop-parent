package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:06:56
 * @Desc:
 */
public interface ProductSpecService {

    // 分页查询商品规格
    PageResult<ProductSpec> page(Integer pageNum, Integer pageSize);

    // 添加商品规格
    void save(ProductSpec productSpec);

    // 修改商品规格
    void updateById(ProductSpec productSpec);

    // 删除商品规格
    void removeById(Long id);

    // 查询所有商品规格
    List<ProductSpec> list();

}
