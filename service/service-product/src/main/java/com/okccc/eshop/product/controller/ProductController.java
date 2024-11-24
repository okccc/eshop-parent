package com.okccc.eshop.product.controller;

import com.github.pagehelper.PageInfo;
import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.dto.h5.ProductSkuDto;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/5/21 19:02:25
 * @Desc:
 */
@Tag(name = "h5商品接口")
@RestController
@RequestMapping(value="/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "分页查询商品")
    @GetMapping(value = "/{pageNum}/{pageSize}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, ProductSkuDto productSkuDto) {
        // 进入商品列表的入口：首页一级分类、首页关键字搜索、分类频道三级分类、首页畅销商品
        PageInfo<ProductSku> pageInfo = productService.page(pageNum, pageSize, productSkuDto);
        return Result.ok(pageInfo);
    }
}
