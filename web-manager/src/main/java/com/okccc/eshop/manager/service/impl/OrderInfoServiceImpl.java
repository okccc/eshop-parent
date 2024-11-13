package com.okccc.eshop.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.okccc.eshop.manager.service.OrderInfoService;
import com.okccc.eshop.model.dto.order.OrderStatisticsDto;
import com.okccc.eshop.model.entity.order.OrderStatistics;
import com.okccc.eshop.model.vo.order.OrderStatisticsVo;
import com.okccc.eshop.manager.mapper.OrderStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:51:14
 * @Desc:
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        // 根据条件查询统计结果
        log.info("订单管理 - 根据条件查询统计结果");
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);

        // 日期列表
        List<String> dateList = new ArrayList<>();

        // 统计金额列表
        ArrayList<BigDecimal> amountList = new ArrayList<>();

        // 遍历集合
        for (OrderStatistics orderStatistics : orderStatisticsList) {
            dateList.add(DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"));
            amountList.add(orderStatistics.getTotalAmount());
        }

        // 封装响应数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
