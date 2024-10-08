package com.okccc.eshop.manager.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/9/9 14:50:21
 * @Desc: Mybatis使用的是PageHelper插件,将其返回结果封装成和MybatisPlus分页插件一样的格式
 */
@Schema(description = "封装分页查询结果的实体类")
@Data
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    @Schema(description = "当前页码")
    private int current;

    @Schema(description = "页面大小")
    private int size;

    @Schema(description = "总页数")
    private int pages;

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "当前页数据列表")
    private List<T> records;
}
