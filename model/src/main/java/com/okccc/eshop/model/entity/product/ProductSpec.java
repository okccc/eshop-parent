package com.okccc.eshop.model.entity.product;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:04:46
 * @Desc:
 */
@Schema(description = "商品规格实体类")
@Data
public class ProductSpec extends BaseEntity {

	@Schema(description = "规格名称")
	private String specName;

	@Schema(description = "规格值")
	private String specValue;

}