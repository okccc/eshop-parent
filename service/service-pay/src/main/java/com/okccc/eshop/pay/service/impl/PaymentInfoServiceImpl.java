package com.okccc.eshop.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.okccc.eshop.client.feign.OrderFeignClient;
import com.okccc.eshop.client.feign.ProductFeignClient;
import com.okccc.eshop.model.dto.product.SkuSaleDto;
import com.okccc.eshop.model.entity.order.OrderInfo;
import com.okccc.eshop.model.entity.order.OrderItem;
import com.okccc.eshop.model.entity.pay.PaymentInfo;
import com.okccc.eshop.pay.mapper.PaymentInfoMapper;
import com.okccc.eshop.pay.service.PaymentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductFeignClient productFeignClient;

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

    @Transactional
    @Override
    public void updatePaymentStatus(Map<String, String> paramMap) {
        // 1.根据订单编号查询支付记录信息
        log.info("支付微服务 - 根据orderNo查询支付信息：{}", paramMap.get("out_trade_no"));
        PaymentInfo paymentInfo = paymentInfoMapper.selectByOrderNo(paramMap.get("out_trade_no"));
        // 如果支付记录已经完成,不需要更新
        if (paymentInfo.getPaymentStatus() == 1) {
            return;
        }

        // 2.更新支付记录状态
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(paramMap.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(paramMap));
        log.info("支付微服务 - 更新支付信息：{}", paymentInfo);
        paymentInfoMapper.updatePaymentInfo(paymentInfo);

        // 3.更新订单状态
        log.info("支付微服务 - 远程调用 - 更新订单状态：{},{}", paymentInfo.getOrderNo(), paymentInfo.getPaymentStatus());
        orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo(), paymentInfo.getPaymentStatus());

        // 4.更新商品销量
        log.info("支付微服务 - 远程调用 - 根据orderNo获取订单信息：{}", paymentInfo.getOrderNo());
        OrderInfo orderInfo = orderFeignClient.getByOrderNo(paymentInfo.getOrderNo());
        List<OrderItem> orderItemList = orderInfo.getOrderItemList();
        ArrayList<SkuSaleDto> skuSaleDtoList = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            SkuSaleDto skuSaleDto = new SkuSaleDto();
            skuSaleDto.setSkuId(orderItem.getSkuId());
            skuSaleDto.setNum(orderItem.getSkuNum());
            skuSaleDtoList.add(skuSaleDto);
        }
        // 远程调用：更新商品销量
        log.info("支付微服务 - 远程调用 - 更新商品销量：{}", skuSaleDtoList);
        productFeignClient.updateSkuSaleNum(skuSaleDtoList);
    }

}
