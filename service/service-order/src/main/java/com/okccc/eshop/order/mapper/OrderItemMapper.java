package com.okccc.eshop.order.mapper;

import com.okccc.eshop.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/7/10 11:05:36
 * @Desc:
 */
@Mapper
public interface OrderItemMapper {

    // 新增订单明细
    void insert(OrderItem orderItem);

    // 根据orderId查询所有订单明细
    List<OrderItem> selectListByOrderId(Long orderId);
}
