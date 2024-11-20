package com.okccc.eshop.product.service.impl;

import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.product.mapper.CategoryMapper;
import com.okccc.eshop.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:30:20
 * @Desc: 首页数据使用缓存后,注意观察微服务日志的数据库查询情况
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getOneCategory() {
        // 查询一级分类
        log.info("分类管理 - 从mysql数据库查询一级分类");
        List<Category> categoryList = categoryMapper.selectOneCategory();
        return categoryList;
    }

}
