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

    // 根据productId查询商品详情
    ProductDetail selectByProductId(Long productId);

    // 添加商品详情
    void insert(ProductDetail productDetail);

    // 修改商品详情
    void updateById(ProductDetail productDetail);

    // 根据id删除商品详情
    void deleteByProductId(Long productId);
}
