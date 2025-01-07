package com.okccc.eshop.order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: okccc
 * @Date: 2024/10/10 16:33:01
 * @Desc: 接口文档：http://localhost:8514/doc.html
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("订单微服务API")
                        .description("订单微服务API")
                        .contact(new Contact().name("okccc"))
                        .version("1.0")
        );
    }

    @Bean
    public GroupedOpenApi systemAPI() {
        // 创建api接口分组
        return GroupedOpenApi.builder()
                // 分组名称
                .group("订单管理")
                // 接口请求路径规则
                .pathsToMatch("/api/order/orderInfo/**")
                .build();
    }
}
