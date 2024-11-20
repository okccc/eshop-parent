package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/22 10:39:04
 * @Desc:
 */
@Mapper
public interface ProductSkuMapper {

    // 根据销量排序
    List<ProductSku> selectListBySale();
}
