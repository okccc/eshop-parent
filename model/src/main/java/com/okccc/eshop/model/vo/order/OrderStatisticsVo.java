package com.okccc.eshop.model.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:08:03
 * @Desc:
 */
@Schema(description = "订单统计响应结果实体类")
@Data
public class OrderStatisticsVo {

    @Schema(description = "日期列表")
    private List<String> dateList;

    @Schema(description = "订单总金额列表")
    private List<BigDecimal> amountList;
}
