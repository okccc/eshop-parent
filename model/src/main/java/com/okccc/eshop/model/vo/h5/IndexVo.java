package com.okccc.eshop.model.vo.h5;

import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.model.entity.product.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:25:02
 * @Desc:
 */
@Schema(description = "h5首页响应结果实体类")
@Data
@AllArgsConstructor
public class IndexVo {

    @Schema(description = "一级分类列表")
    private List<Category> categoryList;

    @Schema(description = "畅销商品列表")
    private List<ProductSku> productSkuList;

}