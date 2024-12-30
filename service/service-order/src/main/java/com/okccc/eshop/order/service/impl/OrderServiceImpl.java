package com.okccc.eshop.order.service.impl;

import com.okccc.eshop.client.feign.CartFeignClient;
import com.okccc.eshop.client.feign.ProductFeignClient;
import com.okccc.eshop.client.feign.UserFeignClient;
import com.okccc.eshop.common.handler.MyRuntimeException;
import com.okccc.eshop.common.result.ResultCodeEnum;
import com.okccc.eshop.common.util.ThreadLocalUtil;
import com.okccc.eshop.model.dto.h5.OrderInfoDto;
import com.okccc.eshop.model.entity.h5.CartInfo;
import com.okccc.eshop.model.entity.order.OrderInfo;
import com.okccc.eshop.model.entity.order.OrderItem;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.model.entity.user.UserAddress;
import com.okccc.eshop.model.vo.h5.TradeVo;
import com.okccc.eshop.order.mapper.OrderInfoMapper;
import com.okccc.eshop.order.mapper.OrderItemMapper;
import com.okccc.eshop.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/6/4 14:07:28
 * @Desc:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public TradeVo createOrder() {
        // 1.远程调用：获取购物车选中的商品列表
        log.info("订单微服务 - 远程调用 - 获取购物车选中商品");
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();

        // 2.订单明细(结算商品)列表
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 3.订单总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        // 4.封装响应结果
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return tradeVo;
    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        // 1.获取前端页面提交参数
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        Long userAddressId = orderInfoDto.getUserAddressId();
        BigDecimal feightFee = orderInfoDto.getFeightFee();
        String remark = orderInfoDto.getRemark();

        // 参数校验
        if (orderItemList.isEmpty()) {
            throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
        }

        for (OrderItem orderItem : orderItemList) {
            // 远程调用：根据skuId获取商品sku信息
            log.info("订单微服务 - 远程调用 - 根据skuId查询商品sku：{}", orderItem.getSkuId());
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if (productSku == null) {
                throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
            }

            // 查看订单明细的商品库存是否充足
            if (orderItem.getSkuNum() > productSku.getStockNum()) {
                throw new MyRuntimeException(ResultCodeEnum.STOCK_LESS);
            }
        }

        // 2.保存订单信息
        OrderInfo orderInfo = new OrderInfo();
        // 订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        // 用户id
        orderInfo.setUserId(ThreadLocalUtil.getUser());
        // 远程调用：根据地址id获取地址信息
        log.info("订单微服务 - 远程调用 - 根据id获取用户地址：{}", userAddressId);
        UserAddress userAddress = userFeignClient.getUserAddressById(userAddressId);
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        // 订单总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(feightFee);
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        orderInfo.setRemark(remark);
        log.info("订单微服务 - 保存订单：{}", orderInfo);
        orderInfoMapper.insert(orderInfo);

        // 3.保存订单明细
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            log.info("订单微服务 - 保存订单明细：{}", orderItem);
            orderItemMapper.insert(orderItem);
        }

        // 4.远程调用：删除购物车已生成订单的商品
        log.info("订单微服务 - 远程调用 - 删除购物车已生成订单的商品");
        cartFeignClient.deleteChecked();

        // 5.返回订单id
        return orderInfo.getId();
    }

    @Override
    public OrderInfo getById(Long id) {
        log.info("订单微服务 - 根据id查询订单：{}", id);
        return orderInfoMapper.selectById(id);
    }
}
