package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.dto.product.CategoryBrandDto;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 14:21:26
 * @Desc:
 */
@Mapper
public interface CategoryBrandMapper {

    // 分页查询,带搜索条件
    Page<CategoryBrand> selectPage(CategoryBrandDto categoryBrandDto);

    // 添加分类品牌
    void insert(CategoryBrand categoryBrand);

    // 修改分类品牌
    void updateById(CategoryBrand categoryBrand);

    // 根据id删除分类品牌
    void deleteById(Long id);

    // 根据categoryId查询三级分类对应的品牌
    List<Brand> selectBrandListByCategoryId(Long categoryId);
}
