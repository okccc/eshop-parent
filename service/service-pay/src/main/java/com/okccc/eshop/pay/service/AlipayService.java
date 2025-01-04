package com.okccc.eshop.pay.service;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:44:05
 * @Desc:
 */
public interface AlipayService {

    // 确认支付
    String submitAlipay(String orderNo);
}
