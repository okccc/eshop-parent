package com.okccc.eshop.model.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:08:03
 * @Desc:
 */
@Data
@Schema(description = "搜索条件实体类")
public class OrderStatisticsDto {

    @Schema(description = "开始时间")
    private String createTimeBegin;

    @Schema(description = "结束时间")
    private String createTimeEnd;

}
