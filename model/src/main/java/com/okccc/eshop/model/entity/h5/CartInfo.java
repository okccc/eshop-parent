package com.okccc.eshop.model.entity.h5;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: okccc
 * @Date: 2024/5/27 11:44:46
 * @Desc: 实体类不一定对应一张mysql表,比如购物车数据完全使用redis存储
 */
@Data
@Schema(description = "购物车实体类")
public class CartInfo extends BaseEntity {
   
   private static final long serialVersionUID = 1L;

   @Schema(description = "用户id")
   private Long userId;

   @Schema(description = "skuId")
   private Long skuId;

   @Schema(description = "放入购物车时价格")
   private BigDecimal cartPrice;

   @Schema(description = "数量")
   private Integer skuNum;

   @Schema(description = "图片文件")
   private String imgUrl;

   @Schema(description = "sku名称(冗余)")
   private String skuName;

   @Schema(description = "商品是否选中(1选中,0取消)")
   private Integer isChecked;
    
}