package com.okccc.eshop.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/7/15 18:48:38
 * @Desc:
 */
@Data
@Schema(description = "商品销量请求参数实体类")
public class SkuSaleDto {

	@Schema(description = "skuId")
	private Long skuId;

	@Schema(description = "数量")
	private Integer num;

}
