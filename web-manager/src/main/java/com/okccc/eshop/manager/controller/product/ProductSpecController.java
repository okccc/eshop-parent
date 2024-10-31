package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.ProductSpecService;
import com.okccc.eshop.model.entity.product.ProductSpec;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:05:40
 * @Desc:
 */
@Tag(name = "商品规格接口")
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    @Operation(summary = "分页查询商品规格")
    @GetMapping(value = "/page")
    public Result<PageResult<ProductSpec>> page(Integer pageNum, Integer pageSize) {
        PageResult<ProductSpec> pageResult = productSpecService.page(pageNum, pageSize);
        return Result.ok(pageResult);
    }

    @Operation(summary = "添加商品规格")
    @PostMapping
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.ok();
    }

    @Operation(summary = "根据id修改商品规格")
    @PutMapping
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.ok();
    }

    @Operation(summary = "根据id删除商品规格")
    @DeleteMapping(value = "/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        productSpecService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "查询所有商品规格")
    @GetMapping(value = "/list")
    public Result list() {
        List<ProductSpec> list = productSpecService.list();
        return Result.ok(list);
    }

}
