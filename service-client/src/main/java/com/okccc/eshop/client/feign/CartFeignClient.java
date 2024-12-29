package com.okccc.eshop.client.feign;

import com.okccc.eshop.client.config.FeignConfig;
import com.okccc.eshop.model.entity.h5.CartInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/6/28 17:26:34
 * @Desc:
 */
@FeignClient(value = "service-cart", configuration = FeignConfig.class)
public interface CartFeignClient {

    @Operation(summary = "获取购物车选中的商品列表")
    @GetMapping(value = "/api/order/cart/auth/getAllChecked")
    List<CartInfo> getAllChecked();

    @Operation(summary = "删除购物车已生成订单的商品")
    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    void deleteChecked();

}
