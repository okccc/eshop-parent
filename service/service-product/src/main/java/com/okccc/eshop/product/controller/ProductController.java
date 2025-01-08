package com.okccc.eshop.product.controller;

import com.github.pagehelper.PageInfo;
import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.dto.h5.ProductSkuDto;
import com.okccc.eshop.model.dto.product.SkuSaleDto;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.model.vo.h5.ProductItemVo;
import com.okccc.eshop.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "根据skuId查询商品详情")
    @GetMapping(value = "/item/{skuId}")
    public Result<ProductItemVo> getById(@PathVariable("skuId") Long skuId) {
        // 商品详情包含：商品基本信息、商品sku信息、轮播图信息、商品规格等
        ProductItemVo productItemVo = productService.getById(skuId);
        return Result.ok(productItemVo);
    }

    @Operation(summary = "根据skuId查询sku信息")
    @GetMapping(value = "/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable("skuId") Long skuId) {
        // 远程调用：购物车模块需要根据skuId查询sku信息,不是前端请求所以不需要返回Result
        return productService.getBySkuId(skuId);
    }

    @Operation(summary = "更新商品销量和库存")
    @PostMapping(value = "/updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        // 远程调用：支付成功需要更新商品销量和库存,不是前端请求所以不需要返回Result
        return productService.updateSkuSaleAndStock(skuSaleDtoList);
    }
}
