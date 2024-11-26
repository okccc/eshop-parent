package com.okccc.eshop.model.vo.h5;

import com.alibaba.fastjson2.JSONArray;
import com.okccc.eshop.model.entity.product.Product;
import com.okccc.eshop.model.entity.product.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/5/22 15:02:06
 * @Desc:
 */
@Data
@Schema(description = "商品详情响应结果实体类")
public class ProductItemVo {

   @Schema(description = "商品信息")
   private Product product;

   @Schema(description = "商品sku信息")
   private ProductSku productSku;

   @Schema(description = "商品详情图片列表")
   private List<String> detailsImageUrlList;

   @Schema(description = "商品轮播图列表")
   private List<String> sliderUrlList;

   @Schema(description = "商品规格信息")
   private JSONArray specValueList;

   @Schema(description = "商品规格对应的商品skuId信息")
   private Map<String, Object> skuSpecValueMap;

}