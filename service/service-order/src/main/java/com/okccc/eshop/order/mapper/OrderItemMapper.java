package com.okccc.eshop.order.mapper;

import com.okccc.eshop.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/7/10 11:05:36
 * @Desc:
 */
@Mapper
public interface OrderItemMapper {

    // 新增订单明细
    void insert(OrderItem orderItem);
}
