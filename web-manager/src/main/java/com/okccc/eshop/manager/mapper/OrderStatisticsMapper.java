package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.dto.order.OrderStatisticsDto;
import com.okccc.eshop.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:31:19
 * @Desc:
 */
@Mapper
public interface OrderStatisticsMapper {

    // 添加订单统计数据
    void insert(OrderStatistics orderStatistics);

    // 条件查询订单统计数据
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
