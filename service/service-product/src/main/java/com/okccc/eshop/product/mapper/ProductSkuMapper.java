package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.dto.h5.ProductSkuDto;
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

    // 分页查询,带搜索条件
    List<ProductSku> selectPage(ProductSkuDto productSkuDto);
}
