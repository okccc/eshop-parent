package com.okccc.eshop.product.service.impl;

import com.alibaba.fastjson2.JSON;
import com.okccc.eshop.common.constant.RedisConstant;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.product.mapper.CategoryMapper;
import com.okccc.eshop.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 查询一级分类：演示RedisTemplate实现缓存功能
     */
    @Override
    public List<Category> getOneCategory() {
        // 1.先查缓存,命中就直接返回
        String value = redisTemplate.opsForValue().get(RedisConstant.CATEGORY_ONE);
        if (StringUtils.hasText(value)) {
            log.info("分类管理 - 从redis缓存查询一级分类");
            List<Category> categoryList = JSON.parseArray(value, Category.class);
            return categoryList;
        }

        // 2.没命中就继续查数据库,并将查询结果更新到缓存
        log.info("分类管理 - 从mysql数据库查询一级分类");
        List<Category> categoryList = categoryMapper.selectOneCategory();
        redisTemplate.opsForValue().set(RedisConstant.CATEGORY_ONE, JSON.toJSONString(categoryList), RedisConstant.CATEGORY_ONE_TTL, TimeUnit.DAYS);

        // 3.返回查询结果
        return categoryList;
    }

    /**
     * 查询所有分类：演示SpringCache实现缓存功能
     */
    @Cacheable(cacheNames = "category", key = "'all'")  // category::all
    @Override
    public List<Category> getCategoryTree() {
        // 1.查询所有分类
        log.info("分类管理 - 从mysql数据库查询所有分类");
        List<Category> categoryList = categoryMapper.selectList();

        // 2.遍历所有分类,构建树形结构
        ArrayList<Category> oneCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            // 判断该分类是否是一级分类
            if (category.getParentId() == 0) {
                oneCategoryList.add(category);
            }
        }

        // 遍历一级分类
        for (Category oneCategory : oneCategoryList) {
            ArrayList<Category> twoCategoryList = new ArrayList<>();
            for (Category category : categoryList) {
                // 判断该一级分类下是否有二级分类
                if (category.getParentId().longValue() == oneCategory.getId().longValue()) {
                    twoCategoryList.add(category);
                }
            }
            // 将二级分类封装到一级分类里面
            oneCategory.setChildren(twoCategoryList);

            // 遍历二级分类
            for (Category twoCategory : twoCategoryList) {
                ArrayList<Category> threeCategoryList = new ArrayList<>();
                for (Category category : categoryList) {
                    // 判断该二级分类下是否有三级分类
                    if (category.getParentId().longValue() == twoCategory.getId().longValue()) {
                        threeCategoryList.add(category);
                    }
                }
                // 将三级分类封装到二级分类里面
                twoCategory.setChildren(threeCategoryList);
            }
        }

        // 3.返回一级分类树形图
        return oneCategoryList;
    }

}
