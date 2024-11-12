package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:31:19
 * @Desc:
 */
@Mapper
public interface OrderStatisticsMapper {

    // 添加订单统计数据
    void insert(OrderStatistics orderStatistics);

}
