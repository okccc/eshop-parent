package com.okccc.eshop.manager.controller.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:40:37
 * @Desc:
 *
 * SPU = Standard Product Unit(标准化产品单元),iphone15
 * SKU = Stock Keeping Unit(库存量单元),iphone15/移动版/128g/土豪金
 */
@Tag(name = "商品接口")
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
}
