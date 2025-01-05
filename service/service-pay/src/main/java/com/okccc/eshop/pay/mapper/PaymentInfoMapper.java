package com.okccc.eshop.pay.mapper;

import com.okccc.eshop.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/7/15 16:26:08
 * @Desc:
 */
@Mapper
public interface PaymentInfoMapper {

    // 根据orderNo查询支付
    PaymentInfo selectByOrderNo(String orderNo);

    // 新增支付
    void insert(PaymentInfo paymentInfo);

    // 更新支付状态
    void updatePaymentInfo(PaymentInfo paymentInfo);
}
