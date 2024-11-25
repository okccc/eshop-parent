package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/21 18:54:11
 * @Desc:
 */
@Mapper
public interface BrandMapper {

    // 查询所有品牌
    List<Brand> selectList();
}
