package com.okccc.eshop.manager.controller.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:22:49
 * @Desc: Spring Task定时任务
 */
@Slf4j
@Component
public class OrderStatisticsTask {

    // 测试定时任务,应用启动后每5秒执行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        log.info("it is test");
    }
}
