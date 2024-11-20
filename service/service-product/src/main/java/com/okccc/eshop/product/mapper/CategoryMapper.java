package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:30:39
 * @Desc:
 */
@Mapper
public interface CategoryMapper {

    // 查询一级分类
    List<Category> selectOneCategory();

}
