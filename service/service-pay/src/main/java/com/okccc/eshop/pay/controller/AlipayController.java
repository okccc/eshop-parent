package com.okccc.eshop.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.pay.config.AlipayProperties;
import com.okccc.eshop.pay.service.AlipayService;
import com.okccc.eshop.pay.service.PaymentInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:43:08
 * @Desc: 支付接口 http://localhost:8515/doc.html
 */
@Tag(name = "支付接口")
@RestController
@RequestMapping(value = "/api/order/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Operation(summary = "支付宝支付")
    @GetMapping(value = "/auth/submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@PathVariable(value = "orderNo") String orderNo) {
        // 点击确认支付按钮会对接第三方支付系统(支付宝/微信/银联)
        String form = alipayService.submitAlipay(orderNo);
        return Result.ok(form);
    }

    @Operation(summary = "支付宝异步回调")
    @GetMapping(value = "/callback/notify")
    public String alipayNotify(@RequestParam Map<String, String> paramMap) {
        // 调用SDK验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(
                    paramMap, alipayProperties.getAlipayPublicKey(), AlipayProperties.charset, AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        System.out.println("paramMap = " + paramMap);

        String trade_status = paramMap.get("trade_status");
        if (signVerified) {
            if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
                // 支付成功,更新交易记录状态
                paymentInfoService.updatePaymentStatus(paramMap);
                return "success";
            }
        } else {
            return "failure";
        }
        return "failure";
    }
}
