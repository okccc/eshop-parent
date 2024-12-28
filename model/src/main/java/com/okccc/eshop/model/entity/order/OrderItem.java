package com.okccc.eshop.model.entity.order;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:08:03
 * @Desc:
 */
@Schema(description = "订单项实体类")
@Data
public class OrderItem extends BaseEntity {

   private static final long serialVersionUID = 1L;

   @Schema(description = "订单id")
   private Long orderId;

   @Schema(description = "商品sku编号")
   private Long skuId;

   @Schema(description = "商品sku名字")
   private String skuName;

   @Schema(description = "商品sku图片")
   private String thumbImg;

   @Schema(description = "商品sku价格")
   private BigDecimal skuPrice;

   @Schema(description = "商品购买数量")
   private Integer skuNum;

}