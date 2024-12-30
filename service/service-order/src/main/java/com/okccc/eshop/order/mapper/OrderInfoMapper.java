package com.okccc.eshop.order.mapper;

import com.okccc.eshop.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/7/10 11:05:18
 * @Desc:
 */
@Mapper
public interface OrderInfoMapper {

    // 新增订单
    void insert(OrderInfo orderInfo);

    // 根据id查询订单
    OrderInfo selectById(Long id);
}
