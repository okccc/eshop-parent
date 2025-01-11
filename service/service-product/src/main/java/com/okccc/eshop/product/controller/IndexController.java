package com.okccc.eshop.product.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.model.vo.h5.IndexVo;
import com.okccc.eshop.product.service.CategoryService;
import com.okccc.eshop.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:28:58
 * @Desc: @Autowired注解提示黄色波浪线 Field injection is not recommended
 *
 * Spring不推荐字段注入的方式,可以用构造函数注入代替,但是成员变量很多的话就要写一堆构造函数
 * lombok提供的@AllArgsController注解会给所有成员变量创建构造函数,但是有些成员变量并不需要构造函数初始化
 * 可以给需要的变量添加final修饰符,将其变成常量后就必须初始化,Variable 'productService' might not have been initialized
 * lombok提供的@RequiredArgsConstructor注解是给有需要的成员变量创建构造函数(详见源码注释)
 */
@Tag(name = "h5首页接口")
@RestController
@RequestMapping(value="/api/product/index")
@RequiredArgsConstructor
public class IndexController {

//    @Autowired
    private final CategoryService categoryService;

//    @Autowired
    private final ProductService productService;

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
