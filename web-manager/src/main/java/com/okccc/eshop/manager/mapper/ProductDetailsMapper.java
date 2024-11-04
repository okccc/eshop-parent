package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.product.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/9 18:18:34
 * @Desc:
 */
@Mapper
public interface ProductDetailsMapper {

    // 添加商品详情
    void insert(ProductDetail productDetail);
}
