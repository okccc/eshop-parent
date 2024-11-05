package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 18:17:36
 * @Desc:
 */
@Mapper
public interface ProductSkuMapper {

    // 添加商品sku
    void insert(ProductSku productSku);

    // 修改商品sku
    void updateById(ProductSku productSku);
}
