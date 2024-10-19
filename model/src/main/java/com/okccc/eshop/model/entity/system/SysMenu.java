package com.okccc.eshop.model.entity.system;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc:
 */
@Schema(description = "系统菜单实体类")
@Data
public class SysMenu extends BaseEntity {

	@Schema(description = "父节点id")
	private Long parentId;

	@Schema(description = "菜单标题")
	private String title;

	@Schema(description = "菜单路由")
	private String component;

	@Schema(description = "排序值")
	private Integer sortValue;

	@Schema(description = "状态(1正常,0禁止)")
	private Integer status;

	// 新增子节点列表,用于构建树形菜单
	@Schema(description = "当前菜单的子菜单")
	private List<SysMenu> children;
}