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
 *
 * 添加商品需要调用六个接口
 * 1.查询所有分类数据,在添加商品的分类下拉框中展示(category)
 * 2.根据选中的三级分类查询对应的品牌数据,在添加商品的品牌下拉框中展示(brand、category_brand)
 * 3.查询所有商品单元,在添加商品的单元下拉框中展示(product_unit)
 * 4.查询所有商品规格,在添加商品的规格下拉框中展示(product_spec)
 * 5.上传图片接口,包括轮播图和详情图
 * 6.添加商品数据(product、product_sku、product_detail)
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

    @Operation(summary = "根据id查询商品")
    @GetMapping(value = "/{id}")
    public Result<Product> getById(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        return Result.ok(product);
    }

    @Operation(summary = "添加商品")
    @PostMapping
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.ok();
    }

    @Operation(summary = "修改商品")
    @PutMapping
    public Result updateById(@RequestBody Product product) {
        productService.updateById(product);
        return Result.ok();
    }

    @Operation(summary = "根据id删除商品")
    @DeleteMapping(value = "/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        productService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "商品审核")
    @GetMapping(value = "/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable("id") Long id, @PathVariable("auditStatus") Integer auditStatus) {
        // 当点击审核按钮时会需要弹出一个对话框,展示商品详情信息,用户可以点击通过或者驳回按钮对商品进行审核操作
        productService.updateAuditStatusById(id, auditStatus);
        return Result.ok();
    }
}
