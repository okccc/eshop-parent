package com.okccc.eshop.model.entity.product;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:04:46
 * @Desc:
 */
@Schema(description = "商品实体类")
@Data
public class Product extends BaseEntity {

	@Schema(description = "商品名称")
	private String name;

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "一级分类id")
	private Long category1Id;

	@Schema(description = "二级分类id")
	private Long category2Id;

	@Schema(description = "三级分类id")
	private Long category3Id;

	@Schema(description = "计量单位")
	private String unitName;

	@Schema(description = "轮播图url")
	private String sliderUrls;

	@Schema(description = "商品规格值json串")
	private String specValue;

	@Schema(description = "线上状态：0初始值,1上架,-1下架")
	private Integer status;

	@Schema(description = "审核状态：0初始值,1通过,-1未通过")
	private Integer auditStatus;

	@Schema(description = "审核信息")
	private String auditMessage;

	// 扩展属性,封装响应结果
	@Schema(description = "品牌名称")
	private String brandName;

	@Schema(description = "一级分类名称")
	private String category1Name;

	@Schema(description = "二级分类名称")
	private String category2Name;

	@Schema(description = "三级分类名称")
	private String category3Name;

	@Schema(description = "商品sku列表")
	private List<ProductSku> productSkuList;

	@Schema(description = "图片详情列表")
	private String detailsImageUrls;

}