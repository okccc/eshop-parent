package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.CategoryService;
import com.okccc.eshop.model.entity.product.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:29:46
 * @Desc:
 */
@Tag(name = "分类接口")
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "根据parentId查询子分类")
    @GetMapping(value = "/getByParentId/{parentId}")
    public Result<List<Category>> getByParentId(@PathVariable("parentId") Long parentId) {
        // 分类是层级结构,通过id和parent_id绑定关系,分类数据有好几层,一次性加载全部数据会很慢
        // 所以前端采用懒加载方式,默认只显示第一层分类(parentId=0),手动点击再根据parentId去查询子分类
        List<Category> list = categoryService.getByParentId(parentId);
        return Result.ok(list);
    }

}
