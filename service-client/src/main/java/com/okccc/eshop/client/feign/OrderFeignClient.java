package com.okccc.eshop.client.feign;

import com.okccc.eshop.client.config.FeignConfig;
import com.okccc.eshop.model.entity.order.OrderInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: okccc
 * @Date: 2024/7/12 17:49:38
 * @Desc:
 */
@FeignClient(value = "service-order", configuration = FeignConfig.class)
public interface OrderFeignClient {

    @Operation(summary = "根据orderNo获取订单信息")
    @GetMapping(value = "/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    OrderInfo getByOrderNo(@PathVariable("orderNo") String orderNo);

    @Operation(summary = "更新订单状态")
    @GetMapping(value = "/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    void updateOrderStatus(@PathVariable(value = "orderNo") String orderNo, @PathVariable(value = "orderStatus") Integer orderStatus);
}
