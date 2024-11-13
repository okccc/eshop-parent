package com.okccc.eshop.manager.controller.order;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.OrderInfoService;
import com.okccc.eshop.model.dto.order.OrderStatisticsDto;
import com.okccc.eshop.model.vo.order.OrderStatisticsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:50:37
 * @Desc:
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Operation(summary = "根据搜索条件查询订单统计数据")
    @GetMapping(value = "/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.ok(orderStatisticsVo);
    }
}
