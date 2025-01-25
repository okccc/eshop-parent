package com.okccc.eshop.admin.model.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/4/21 21:14:07
 * @Desc: MybatisPlus核心注解
 *
 * 1.@TableName
 * 作用于实体类,默认情况下,实体类名称就是数据库表名(忽略大小写),属性名对应字段名
 * 但不是所有实体类都和数据库表完全映射,比如实体类是User而表名是t_user,此时操作表就会报错
 * 方式1：给每个实体类添加注解标识(推荐)
 * 方式2：在application.yml全局配置,给表名统一加前缀"t_"
 *
 * 2.@TableId
 * 作用于主键列,mysql数据库的主键策略默认是auto_increment,如果要使用其它策略可以添加该注解修改
 * 数据库表太大要做数据拆分
 * 垂直拆分是列拆分,经常查询的列就是热数据,冷热数据是一条数据拆开的,所以是同一个主键,新增数据也就不存在主键重复的问题
 * 水平拆分是行拆分,比如上亿条数据必须分库分表,如果主键策略是自增长,拆分的多个表新增数据会出现主键重复的问题,此时可以用UUID或雪花算法
 * UUID：随机生成一个不重复的字符串,算法复杂且字符串太长效率较低
 * 雪花算法：随机生成一个不重复的数字,实体类用Long或String,数据库用bigint或varchar,解决分布式系统中生成全局唯一id的需求
 *
 * 3.@TableField
 * 作用于非主键列,当实体类属性名和数据库列名不一致时添加该注解指定,还可以设置很多其他参数
 *
 * 4.@Version
 * alter table t_user add version int default 1;
 * 乐观锁版本号,解决并发场景修改数据的安全问题,针对updateById(id)和update(entity,wrapper)
 * 乐观锁和悲观锁是并发编程中处理并发访问和资源竞争的两种不同的锁机制
 *
 * 5.@TableLogic
 * alter table t_user add is_deleted int default 0;
 * 逻辑删除,默认0表示未删除,执行删除操作时会将该属性修改为1,查询操作只查is_deleted=0的数据
 * 添加逻辑删除前是DELETE操作 ==> Preparing: DELETE FROM t_user WHERE id=?
 * 添加逻辑删除后是UPDATE操作 ==> Preparing: UPDATE t_user SET is_deleted=1 WHERE id=? AND is_deleted=0
 * 逻辑删除只对Mybatis-Plus自动生成的sql有效,在Mapper.xml手动编写sql时需要添加过滤条件is_deleted=0
 *
 * 6.@JsonIgnore
 * 给实体类属性添加com.fasterxml.jackson.annotation.JsonIgnore注解,前后端交互时就会忽略该字段
 * SpringBoot默认使用Jackson序列化器,@RequestBody将前端传入的JSON反序列化成Java对象,@ResponseBody将后端响应的Java对象序列化成JSON
 *
 * 前端提交参数通常不包含createTime/updateTime/isDeleted,因此需要手动赋值,数据库中几乎每张表都有上述字段,挨个赋值显然很繁琐
 * 方式1：数据库端实现,在mysql创建表时给这些字段设置默认值(推荐)
 * `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
 * `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
 * `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记'
 * 方式2：后端实现,使用MybatisPlus的自动填充功能,自定义Handler实现MetaObjectHandler接口,然后给@TableField注解添加fill属性
 */
@Schema(description = "公共属性基类")
@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "修改时间")
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @JsonIgnore
    @TableLogic
    private Integer isDeleted;
}