package com.okccc.eshop.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2024/7/15 15:40:36
 * @Desc:
 */
@Data
@Component
@ConfigurationProperties(prefix = "eshop.alipay")
public class AlipayProperties {

    private String alipayUrl;
    private String appId;
    private String appPrivateKey;
    public  String alipayPublicKey;
    public  String returnPaymentUrl;
    public  String notifyPaymentUrl;

    public final static String format = "json";
    public final static String charset = "utf-8";
    public final static String sign_type = "RSA2";
}
