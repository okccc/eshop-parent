package com.okccc.eshop.product.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/21 18:53:14
 * @Desc:
 */
@Tag(name = "h5品牌接口")
@RestController
@RequestMapping(value="/api/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "获取所有品牌")
    @GetMapping("findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> list = brandService.list();
        return Result.ok(list);
    }
}
