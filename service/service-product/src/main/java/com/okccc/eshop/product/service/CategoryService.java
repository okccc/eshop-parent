package com.okccc.eshop.product.service;

import com.okccc.eshop.model.entity.product.Category;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:30:12
 * @Desc:
 */
public interface CategoryService {

    // 查询一级分类
    List<Category> getOneCategory();

}
