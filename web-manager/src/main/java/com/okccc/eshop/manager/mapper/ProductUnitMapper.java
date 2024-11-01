package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.product.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 17:05:08
 * @Desc:
 */
@Mapper
public interface ProductUnitMapper {

    // 查询所有商品单元
    List<ProductUnit> selectList();
}
