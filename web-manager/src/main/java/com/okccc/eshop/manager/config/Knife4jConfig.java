package com.okccc.eshop.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: okccc
 * @Date: 2024/8/1 15:43:20
 * @Desc: 接口文档：http://localhost:8501/doc.html
 *
 * 常用注解：@Schema标注实体类/@Tag标注Controller类/@Operation标注方法/@Parameter标注参数/@ApiResponse标注响应状态码
 *
 * Knife4j注解没生效？
 * SpringBoot默认只扫描启动类所在包及其子包,可以在启动类添加注解@ComponentScan指定扫描范围
 *
 * 每个子模块都有单独的接口文档Knife4jConfig
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("后台管理系统API")
                        .description("后台管理系统API")
                        .contact(new Contact().name("okccc"))
                        .version("1.0")
        );
    }

    @Bean
    public GroupedOpenApi systemAPI() {
        // 创建api接口分组
        return GroupedOpenApi.builder()
                // 分组名称
                .group("系统管理")
                // 接口请求路径规则
                .pathsToMatch("/admin/system/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginAPI() {
        return GroupedOpenApi.builder()
                .group("商品管理")
                .pathsToMatch("/admin/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder()
                .group("订单管理")
                .pathsToMatch("/admin/order/**")
                .build();
    }
}
