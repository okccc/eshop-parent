package com.okccc.eshop.manager.service;

import com.okccc.eshop.model.dto.order.OrderStatisticsDto;
import com.okccc.eshop.model.vo.order.OrderStatisticsVo;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:51:04
 * @Desc:
 */
public interface OrderInfoService {

    // 订单统计
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
