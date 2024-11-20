package com.okccc.eshop.product.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.model.vo.h5.IndexVo;
import com.okccc.eshop.product.service.CategoryService;
import com.okccc.eshop.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:28:58
 * @Desc:
 */
@Tag(name = "h5首页接口")
@RestController
@RequestMapping(value="/api/product/index")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> getIndexData(){
        // 查询一级分类
        List<Category> categoryList = categoryService.getOneCategory();

        // 查询畅销商品
        List<ProductSku> productSkuList = productService.getProductSkuBySale();

        // 返回h5首页数据
        IndexVo indexVo = new IndexVo(categoryList, productSkuList);
        return Result.ok(indexVo);
    }

}
