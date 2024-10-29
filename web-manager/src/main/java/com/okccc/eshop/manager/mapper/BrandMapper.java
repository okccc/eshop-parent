package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/9 10:38:58
 * @Desc:
 */
@Mapper
public interface BrandMapper {

    // 分页查询品牌
    Page<Brand> selectPage();

    // 添加品牌
    void insert(Brand brand);

    // 修改品牌
    void updateById(Brand brand);

    // 根据id删除品牌
    void deleteById(Long id);
}
