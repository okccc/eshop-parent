package com.okccc.eshop.model.entity.product;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/8/30 16:56:02
 * @Desc:
 */
@Schema(description = "分类品牌实体类")
@Data
public class CategoryBrand extends BaseEntity {

    @Schema(description = "分类id")
    private Long categoryId;

    @Schema(description = "品牌id")
    private Long brandId;

    // 扩展属性,封装响应结果
    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "品牌logo")
    private String logo;
}
