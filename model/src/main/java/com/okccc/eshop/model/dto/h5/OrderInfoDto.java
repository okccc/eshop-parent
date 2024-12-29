package com.okccc.eshop.model.dto.h5;

import com.okccc.eshop.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/7/10 10:46:49
 * @Desc:
 */
@Data
@Schema(description = "下单请求参数实体类")
public class OrderInfoDto {

    @Schema(description = "用户地址id")
    private Long userAddressId;

    @Schema(description = "订单明细列表")
    private List<OrderItem> orderItemList;

    @Schema(description = "运费")
    private BigDecimal feightFee;

    @Schema(description = "备注")
    private String remark;
}
