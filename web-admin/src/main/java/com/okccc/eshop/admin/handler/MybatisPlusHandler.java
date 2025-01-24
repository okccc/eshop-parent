package com.okccc.eshop.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/7/24 15:24:32
 * @Desc: mysql通常会指定默认值 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // insert数据时自动将create_time字段填充为系统当前时间
        strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // update数据时自动将update_time字段填充为系统当前时间
        strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
