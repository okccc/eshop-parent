package com.okccc.eshop.product.service.impl;

import com.alibaba.fastjson2.JSON;
import com.okccc.eshop.common.constant.RedisConstant;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.product.mapper.CategoryMapper;
import com.okccc.eshop.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

}
