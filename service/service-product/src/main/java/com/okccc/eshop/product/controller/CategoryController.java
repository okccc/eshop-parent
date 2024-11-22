package com.okccc.eshop.product.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 14:14:54
 * @Desc:
 */
@Tag(name = "h5分类接口")
@RestController
@RequestMapping(value="/api/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "获取分类数据")
    @GetMapping(value = "/findCategoryTree")
    public Result<List<Category>> findCategoryTree() {
        // 分类是层级结构,这里没有采用懒加载方式,而是一次性加载所有分类
        List<Category> list = categoryService.getCategoryTree();
        return Result.ok(list);
    }

}
