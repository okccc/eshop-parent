package com.okccc.eshop.pay.service.impl;

import com.okccc.eshop.client.feign.OrderFeignClient;
import com.okccc.eshop.model.entity.order.OrderInfo;
import com.okccc.eshop.model.entity.order.OrderItem;
import com.okccc.eshop.model.entity.pay.PaymentInfo;
import com.okccc.eshop.pay.mapper.PaymentInfoMapper;
import com.okccc.eshop.pay.service.PaymentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:45:07
 * @Desc:
 */
@Slf4j
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    public PaymentInfo save(String orderNo) {
        // 1.根据订单编号查询支付信息
        log.info("支付微服务 - 根据orderNo查询支付信息：{}", orderNo);
        PaymentInfo paymentInfo = paymentInfoMapper.selectByOrderNo(orderNo);

        // 2.判断支付信息是否存在,存在就不用重复添加(订单支付失败后可以继续支付)
        if (paymentInfo == null) {
            // 远程调用：根据orderNo获取订单信息
            log.info("支付微服务 - 远程调用 - 根据orderNo获取订单信息：{}", orderNo);
            OrderInfo orderInfo = orderFeignClient.getByOrderNo(orderNo);

            // 封装PaymentInfo对象
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            StringBuilder sb = new StringBuilder();
            for(OrderItem orderItem : orderInfo.getOrderItemList()) {
                sb.append(orderItem.getSkuName()).append(" ");
            }
            paymentInfo.setContent(sb.toString());
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);

            // 保存支付信息
            log.info("支付微服务 - 保存支付信息：{}", paymentInfo);
            paymentInfoMapper.insert(paymentInfo);
        }

        // 3.返回支付信息
        return paymentInfo;
    }

}
