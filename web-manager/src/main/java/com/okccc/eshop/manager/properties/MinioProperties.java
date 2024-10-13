package com.okccc.eshop.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2024/9/12 17:51:39
 * @Desc:
 */
@Data
@Component
@ConfigurationProperties(prefix = "eshop.minio")
public class MinioProperties {

    // 服务器地址
    private String endpoint;

    // 账号
    private String accessKey;

    // 密码
    private String secretKey;

    // 桶名
    private String bucketName;
}
