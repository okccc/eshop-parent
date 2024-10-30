package com.okccc.eshop.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/9 14:04:35
 * @Desc:
 */
@Schema(description = "分类品牌搜索请求参数实体类")
@Data
public class CategoryBrandDto {

	@Schema(description = "分类id")
	private Long categoryId;

	@Schema(description = "品牌id")
	private Long brandId;

}