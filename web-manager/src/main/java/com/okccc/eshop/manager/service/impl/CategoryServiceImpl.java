package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.mapper.CategoryMapper;
import com.okccc.eshop.manager.service.CategoryService;
import com.okccc.eshop.model.entity.product.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:30:21
 * @Desc:
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getByParentId(Long parentId) {
        // 根据父分类查询它下面的所有子分类
        log.info("分类管理 - 根据parentId查询子分类：{}", parentId);
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);

        for (Category category : categoryList) {
            // 判断当前分类是否有子分类
            log.info("分类管理 - 根据id查询子分类数量：{}", category.getId());
            int count = categoryMapper.countByParentId(category.getId());
            category.setHasChildren(count > 0);
        }

        // 返回结果
        return categoryList;
    }

}
