package com.okccc.eshop.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2024/5/23 11:25:15
 * @Desc:
 */
@Data
@Schema(description = "用户响应结果实体类")
public class UserInfoVo {

   @Schema(description = "昵称")
   private String nickName;

   @Schema(description = "头像")
   private String avatar;

}