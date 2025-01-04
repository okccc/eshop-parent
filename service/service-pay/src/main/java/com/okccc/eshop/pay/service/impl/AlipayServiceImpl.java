package com.okccc.eshop.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.okccc.eshop.model.entity.pay.PaymentInfo;
import com.okccc.eshop.pay.config.AlipayProperties;
import com.okccc.eshop.pay.service.AlipayService;
import com.okccc.eshop.pay.service.PaymentInfoService;
import com.okccc.eshop.common.result.ResultCodeEnum;
import com.okccc.eshop.common.handler.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:44:20
 * @Desc:
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Override
    public String submitAlipay(String orderNo) {
        // 1.保存支付信息
        PaymentInfo paymentInfo = paymentInfoService.save(orderNo);

        // 2.构建支付请求对象
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        // 同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());
        // 异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());
        // 封装参数
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("out_trade_no", paymentInfo.getOrderNo());
        hashMap.put("product_code", "QUICK_WAP_WAY");
        hashMap.put("total_amount", new BigDecimal("0.01"));
        hashMap.put("subject", paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(hashMap));

        // 3.调用支付宝服务接口
        try {
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayRequest);
            if (response.isSuccess()) {
                log.info("调用成功");
                return response.getBody();
            } else {
                log.info("调用失败");
                throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
