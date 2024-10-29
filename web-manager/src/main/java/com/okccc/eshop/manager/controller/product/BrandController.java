package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.BrandService;
import com.okccc.eshop.model.entity.product.Brand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 10:37:46
 * @Desc:
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "分页查询品牌")
    @GetMapping(value = "/page")
    public Result<PageResult<Brand>> page(Integer pageNum, Integer pageSize) {
        PageResult<Brand> pageResult = brandService.page(pageNum, pageSize);
        return Result.ok(pageResult);
    }

    @Operation(summary = "添加品牌")
    @PostMapping
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.ok();
    }

    @Operation(summary = "根据id修改品牌")
    @PutMapping
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.ok();
    }

    @Operation(summary = "根据id删除品牌")
    @DeleteMapping(value = "/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        brandService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "查询所有品牌")
    @GetMapping(value = "/list")
    public Result<List<Brand>> list() {
        List<Brand> list = brandService.list();
        return Result.ok(list);
    }

}
