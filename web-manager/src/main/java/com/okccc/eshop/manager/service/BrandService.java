package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.entity.product.Brand;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 10:38:31
 * @Desc:
 */
public interface BrandService {

    // 分页查询品牌
    PageResult<Brand> page(Integer pageNum, Integer pageSize);

    // 添加品牌
    void save(Brand brand);

    // 修改品牌
    void updateById(Brand brand);

    // 删除品牌
    void removeById(Long id);

    // 查询所有品牌
    List<Brand> list();

}
