package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.entity.product.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/22 10:39:47
 * @Desc:
 */
@Mapper
public interface ProductDetailsMapper {

    // 根据productId查询商品详情
    ProductDetail selectById(Long productId);
}
