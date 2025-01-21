package com.okccc.eshop.admin.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/4/25 11:35:33
 * @Desc:
 */
@Data
@Schema(description = "用户搜索请求参数实体类")
public class SysUserDto {

    @Schema(description = "搜索关键字")
    private String keyword;

    @Schema(description = "开始时间")
    private String createTimeBegin;

    @Schema(description = "结束时间")
    private String createTimeEnd;

}
