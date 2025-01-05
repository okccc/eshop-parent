package com.okccc.eshop.pay.service;

import com.okccc.eshop.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:44:57
 * @Desc:
 */
public interface PaymentInfoService {

    // 保存支付信息
    PaymentInfo save(String orderNo);

    // 更新交易记录状态
    void updatePaymentStatus(Map<String, String> paramMap);
}
