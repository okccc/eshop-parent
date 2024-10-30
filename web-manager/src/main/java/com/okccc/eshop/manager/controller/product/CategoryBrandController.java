package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.CategoryBrandService;
import com.okccc.eshop.model.dto.product.CategoryBrandDto;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.model.entity.product.CategoryBrand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 14:20:18
 * @Desc:
 *
 * 分类品牌就是将分类数据和品牌数据进行关联,两者是多对多关系,所以需要单独使用一张表category_brand存储数据
 * 加载分类品牌列表页需要调用三个接口
 * 1.查询所有分类数据,在搜索表单的分类下拉框中展示(category)
 * 2.查询所有品牌数据,在搜索表单的品牌下拉框中展示(brand)
 * 3.分页查询分类品牌数据(category_brand)
 */
@Tag(name = "分类品牌接口")
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @Operation(summary = "分页查询分类品牌")
    @GetMapping(value = "/page")
    public Result<PageResult<CategoryBrand>> page(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        PageResult<CategoryBrand> pageResult = categoryBrandService.page(pageNum, pageSize, categoryBrandDto);
        return Result.ok(pageResult);
    }

    @Operation(summary = "添加分类品牌")
    @PostMapping
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.ok();
    }

    @Operation(summary = "修改分类品牌")
    @PutMapping
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.ok();
    }

    @Operation(summary = "根据id删除分类品牌")
    @DeleteMapping(value = "/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        categoryBrandService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据categoryId查询三级分类对应的品牌数据")
    @GetMapping(value = "/listByCategoryId/{categoryId}")
    public Result listByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Brand> list = categoryBrandService.listByCategoryId(categoryId);
        return Result.ok(list);
    }
}
