package com.okccc.eshop.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/9/12 18:01:02
 * @Desc:
 */
@Data
@Component
@ConfigurationProperties(prefix = "eshop.auth")
public class AuthProperties {

    // 免登录校验路径
    private List<String> noAuthUrls;
}
