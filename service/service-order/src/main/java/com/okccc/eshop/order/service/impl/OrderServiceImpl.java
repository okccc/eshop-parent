package com.okccc.eshop.order.service.impl;

import com.okccc.eshop.client.feign.CartFeignClient;
import com.okccc.eshop.model.entity.h5.CartInfo;
import com.okccc.eshop.model.entity.order.OrderItem;
import com.okccc.eshop.model.vo.h5.TradeVo;
import com.okccc.eshop.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/6/4 14:07:28
 * @Desc:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartFeignClient cartFeignClient;

    @Override
    public TradeVo createOrder() {
        // 1.远程调用：获取购物车选中的商品列表
        log.info("订单微服务 - 远程调用 - 获取购物车选中商品");
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();

        // 2.订单明细(结算商品)列表
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 3.订单总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        // 4.封装响应结果
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return tradeVo;
    }
}
