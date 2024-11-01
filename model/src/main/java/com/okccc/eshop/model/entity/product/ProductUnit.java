package com.okccc.eshop.model.entity.product;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:04:46
 * @Desc:
 */
@Schema(description = "商品单元实体类")
@Data
public class ProductUnit extends BaseEntity {

	@Schema(description = "名称")
	private String name;

}