package com.okccc.eshop.manager.controller.product;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.ProductService;
import com.okccc.eshop.model.dto.product.ProductDto;
import com.okccc.eshop.model.entity.product.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:40:37
 * @Desc:
 *
 * SPU = Standard Product Unit(标准化产品单元),iphone15
 * SKU = Stock Keeping Unit(库存量单元),iphone15/移动版/128g/土豪金
 *
 * 加载商品列表页需要调用三个接口
 * 1.查询所有分类数据,在搜索表单的分类下拉框中展示(category)
 * 2.查询所有品牌数据,在搜索表单的品牌下拉框中展示(brand)
 * 3.分页查询商品数据(product)
 */
@Tag(name = "商品接口")
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "分页查询商品")
    @GetMapping(value = "/page")
    public Result<PageResult<Product>> page(Integer pageNum, Integer pageSize, ProductDto productDto) {
        PageResult<Product> pageResult = productService.page(pageNum, pageSize, productDto);
        return Result.ok(pageResult);
    }
}
