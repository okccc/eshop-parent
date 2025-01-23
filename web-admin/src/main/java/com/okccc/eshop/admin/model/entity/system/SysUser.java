package com.okccc.eshop.admin.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.okccc.eshop.admin.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc:
 */

@Schema(description = "系统用户实体类")
@Data
public class SysUser extends BaseEntity {

	@Schema(description = "用户名")
	private String username;

	// 密码属于敏感信息,应该在查询时过滤掉,所以登录校验时通用Mapper查不到password,需要自定义Mapper
	@Schema(description = "密码")
	@TableField(select = false)
	private String password;

	@Schema(description = "昵称")
	private String name;

	@Schema(description = "手机号码")
	private String phone;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "描述")
	private String description;

	@Schema(description = "状态(1正常,0停用)")
	private Integer status;

}