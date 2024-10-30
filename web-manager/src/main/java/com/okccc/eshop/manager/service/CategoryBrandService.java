package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.dto.product.CategoryBrandDto;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 14:20:58
 * @Desc:
 */
public interface CategoryBrandService {

    // 分页查询,带搜索条件
    PageResult<CategoryBrand> page(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto);

    // 添加分类品牌
    void save(CategoryBrand categoryBrand);

    // 修改分类品牌
    void updateById(CategoryBrand categoryBrand);

    // 删除分类品牌
    void removeById(Long id);

    // 根据categoryId查询三级分类对应的品牌
    List<Brand> listByCategoryId(Long categoryId);
}
