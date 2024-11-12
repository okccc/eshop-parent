package com.okccc.eshop.manager.controller.order;

import cn.hutool.core.date.DateUtil;
import com.okccc.eshop.model.entity.order.OrderStatistics;
import com.okccc.eshop.manager.mapper.OrderInfoMapper;
import com.okccc.eshop.manager.mapper.OrderStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:22:49
 * @Desc: Spring Task定时任务
 */
@Slf4j
@Component
public class OrderStatisticsTask {

    // 测试定时任务,应用启动后每5秒执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void test() {
//        log.info("it is test");
//    }

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    // 每天凌晨2点查询前一天的统计数据添加到统计结果表
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        // 获取前一天日期
        String statDate = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");

        // 统计前一天的交易金额
        log.info("订单管理 - 统计前一天的交易金额：{}", statDate);
        OrderStatistics orderStatistics = orderInfoMapper.statByDate(statDate);

        // 写入统计结果表
        if (orderStatistics != null) {
            log.info("订单管理 - 添加统计结果：{}", orderStatistics);
            orderStatisticsMapper.insert(orderStatistics);
        }

    }
}
