package com.okccc.eshop.model.entity.product;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:27:34
 * @Desc:
 */
@Schema(description = "商品分类实体类")
@Data
public class Category extends BaseEntity {

	@Schema(description = "分类名称")
	private String name;

	@Schema(description = "分类图片url")
	private String imageUrl;

	@Schema(description = "父节点id")
	private Long parentId;

	@Schema(description = "是否显示(1是0否)")
	private Integer status;

	@Schema(description = "排序字段")
	private Integer orderNum;

	// 扩展属性,封装响应结果
	@Schema(description = "是否存在子节点")
	private Boolean hasChildren;

	@Schema(description = "子节点列表")
	private List<Category> children;

}