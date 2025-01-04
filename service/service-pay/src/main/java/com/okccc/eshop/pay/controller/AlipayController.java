package com.okccc.eshop.pay.controller;

import com.okccc.eshop.common.result.Result;
import com.okccc.eshop.pay.service.AlipayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "确认支付")
    @GetMapping(value = "/auth/submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@PathVariable(value = "orderNo") String orderNo) {
        // 点击确认支付按钮会对接第三方支付系统(支付宝/微信/银联)
        String form = alipayService.submitAlipay(orderNo);
        return Result.ok(form);
    }
}
