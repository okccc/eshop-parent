package com.okccc.eshop.model.entity.order;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:08:03
 * @Desc:
 */
@Schema(description = "订单统计实体类")
@Data
public class OrderStatistics extends BaseEntity {

    @Schema(description = "订单日期")
    private Date orderDate;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "订单数")
    private Integer totalNum;
    
}