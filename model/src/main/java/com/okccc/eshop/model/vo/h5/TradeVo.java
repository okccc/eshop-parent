package com.okccc.eshop.model.vo.h5;

import com.okccc.eshop.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/6/4 14:04:37
 * @Desc:
 */
@Data
@Schema(description = "封装结算响应结果的实体类")
public class TradeVo {

    @Schema(description = "结算商品列表")
    private List<OrderItem> orderItemList;

    @Schema(description = "结算总金额")
    private BigDecimal totalAmount;

}