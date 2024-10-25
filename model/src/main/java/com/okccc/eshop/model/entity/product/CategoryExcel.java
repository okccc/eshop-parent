package com.okccc.eshop.model.entity.product;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: okccc
 * @Date: 2024/5/8 18:16:59
 * @Desc: 创建和excel表格对应的实体类,属性就是表头的列,对象就是一行数据
 */
@Schema(description = "商品分类导出Excel实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryExcel {

	// value指定表头名称,index指定顺序
	@ExcelProperty(value = "id", index = 0)
	private Long id;

	@ExcelProperty(value = "名称", index = 1)
	private String name;

	@ExcelProperty(value = "图片url", index = 2)
	private String imageUrl;

	@ExcelProperty(value = "上级id", index = 3)
	private Long parentId;

	@ExcelProperty(value = "状态", index = 4)
	private Integer status;

	@ExcelProperty(value = "排序", index = 5)
	private Integer orderNum;

}