package com.okccc.eshop.order.service;

import com.github.pagehelper.PageInfo;
import com.okccc.eshop.model.dto.h5.OrderInfoDto;
import com.okccc.eshop.model.entity.order.OrderInfo;
import com.okccc.eshop.model.vo.h5.TradeVo;

/**
 * @Author: okccc
 * @Date: 2024/6/4 14:07:17
 * @Desc:
 */
public interface OrderService {

    // 创建订单
    TradeVo createOrder();

    // 提交订单
    Long submitOrder(OrderInfoDto orderInfoDto);

    // 根据id查询订单
    OrderInfo getById(Long id);

    // 立即购买
    TradeVo buy(Long skuId);

    // 分页查询订单
    PageInfo<OrderInfo> page(Integer pageNum, Integer pageSize, Integer orderStatus);
}
