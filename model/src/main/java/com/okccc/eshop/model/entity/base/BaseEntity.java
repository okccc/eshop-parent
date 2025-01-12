package com.okccc.eshop.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc: 所有实体类均实现了Serializable接口,方便将实体对象持久化到redis进行缓存
 */
@Schema(description = "公共属性基类")
@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Schema(description = "逻辑删除")
    private Integer isDeleted;
}