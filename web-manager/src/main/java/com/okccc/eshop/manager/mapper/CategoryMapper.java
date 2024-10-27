package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.model.entity.product.CategoryExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:30:44
 * @Desc:
 */
@Mapper
public interface CategoryMapper {

    // 查询所有分类
    List<Category> selectList();

    // 根据parentId查询子分类
    List<Category> selectByParentId(Long parentId);

    // 根据parentId查询子分类数量
    int countByParentId(Long parentId);

    // 批量添加
    void batchInsert(List<CategoryExcel> cachedDataList);
}
