package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:31:59
 * @Desc:
 */
@Mapper
public interface ProductMapper {

    // 根据id查询商品
    Product selectById(Long id);
}
