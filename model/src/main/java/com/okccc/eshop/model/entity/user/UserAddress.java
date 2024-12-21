package com.okccc.eshop.model.entity.user;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/6/4 10:06:46
 * @Desc:
 */
@Schema(description = "用户地址实体类")
@Data
public class UserAddress extends BaseEntity {

   @Schema(description = "用户ID")
   private UserInfo userId;

   @Schema(description = "用户名称")
   private String name;

   @Schema(description = "电话")
   private String phone;

   @Schema(description = "标签名称")
   private String tagName;

   @Schema(description = "省份编码")
   private String provinceCode;

   @Schema(description = "城市编码")
   private String cityCode;

   @Schema(description = "区县编码")
   private String districtCode;

   @Schema(description = "详细地址")
   private String address;

   @Schema(description = "完整地址")
   private String fullAddress;

   @Schema(description = "是否默认地址(1是,0否)")
   private Integer isDefault;

}