package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:07:31
 * @Desc:
 */
@Mapper
public interface ProductSpecMapper {

    // 分页查询商品规格
    Page<ProductSpec> selectPage();

    // 添加商品规格
    void insert(ProductSpec productSpec);

    // 修改商品规格
    void updateById(ProductSpec productSpec);

    // 根据id删除商品规格
    void deleteById(Long id);
}
