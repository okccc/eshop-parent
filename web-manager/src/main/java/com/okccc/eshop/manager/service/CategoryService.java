package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.entity.product.Category;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:30:12
 * @Desc:
 */
public interface CategoryService {

    // 根据parentId获取子分类
    List<Category> getByParentId(Long parentId);
}
