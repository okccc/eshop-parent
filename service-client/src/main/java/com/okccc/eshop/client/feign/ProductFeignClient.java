package com.okccc.eshop.client.feign;

import com.okccc.eshop.client.config.FeignConfig;
import com.okccc.eshop.model.entity.product.ProductSku;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: okccc
 * @Date: 2024/6/28 16:55:59
 * @Desc: 远程调用的三个关键点：服务名称、请求方式和请求路径、请求参数和返回值类型
 */
@FeignClient(value = "service-product", configuration = FeignConfig.class)  // 1.服务名称
public interface ProductFeignClient {

    @Operation(summary = "根据skuId查询商品sku")
    @GetMapping(value = "/api/product/getBySkuId/{skuId}")  // 2.请求方式和请求路径
    ProductSku getBySkuId(@PathVariable("skuId") Long skuId);  // 3.请求参数和返回值类型

}
