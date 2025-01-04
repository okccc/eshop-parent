package com.okccc.eshop.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: okccc
 * @Date: 2024/7/15 16:50:35
 * @Desc:
 */
@Configuration
public class AlipayConfiguration {

    @Autowired
    private AlipayProperties alipayProperties;

    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(
                alipayProperties.getAlipayUrl(),
                alipayProperties.getAppId(),
                alipayProperties.getAppPrivateKey(),
                AlipayProperties.format,
                AlipayProperties.charset,
                alipayProperties.getAlipayPublicKey(),
                AlipayProperties.sign_type
        );
    }
}
