package com.okccc.eshop.manager.mapper;

import com.okccc.eshop.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:31:00
 * @Desc:
 */
@Mapper
public interface OrderInfoMapper {

    // 按照日期分组统计订单金额
    OrderStatistics statByDate(String statDate);
}
