package com.okccc.eshop.manager.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2024/4/20 12:21:39
 * @Desc:
 *
 * 2024-09-10 15:54:58.249 WARN  85296 --- [http-nio-8501-exec-4]
 * com.zaxxer.hikari.pool.PoolBase: HikariPool - Failed to validate connection com.mysql.cj.jdbc.ConnectionImpl@361b9f4a
 * (No operations allowed after connection closed.). Possibly consider using a shorter maxLifetime value.
 * 问题：查询mysql时不时就会告警数据库连接关闭
 * 分析：HikariPool连接池的MAX_LIFETIME默认30min,正常不会超过mysql的8小时主动断开
 * 1.SHOW VARIABLES LIKE '%wait_timeout%'
 * 2.cat /etc/my.cnf -> wait_timeout
 * 3.问问dba是否有定时删除空闲连接的脚本
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {

}
