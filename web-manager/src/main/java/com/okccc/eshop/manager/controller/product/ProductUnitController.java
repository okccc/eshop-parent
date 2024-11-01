package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.ProductUnitService;
import com.okccc.eshop.model.entity.product.ProductUnit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 17:04:14
 * @Desc: 商品单元接口
 */
@Tag(name = "商品单元接口")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    @Operation(summary = "查询所有商品单元")
    @GetMapping(value = "/list")
    public Result<List<ProductUnit>> list() {
        List<ProductUnit> list = productUnitService.list();
        return Result.ok(list);
    }
}
