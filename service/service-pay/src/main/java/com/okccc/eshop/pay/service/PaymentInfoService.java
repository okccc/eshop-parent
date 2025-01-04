package com.okccc.eshop.pay.service;

import com.okccc.eshop.model.entity.pay.PaymentInfo;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:44:57
 * @Desc:
 */
public interface PaymentInfoService {

    // 保存支付信息
    PaymentInfo save(String orderNo);
}
