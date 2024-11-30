package com.okccc.eshop.cart.service.impl;

import com.alibaba.fastjson2.JSON;
import com.okccc.eshop.cart.service.CartService;
import com.okccc.eshop.feign.product.ProductFeignClient;
import com.okccc.eshop.model.entity.h5.CartInfo;
import com.okccc.eshop.model.entity.product.ProductSku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/5/27 15:54:28
 * @Desc:
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public void save(Long skuId, Integer skuNum) {
        // 获取购物车的key
        String cartKey = "user:cart:1";

        // 根据key和field获取指定商品
        log.info("购物车微服务 - 根据key和field获取指定商品：{},{}", cartKey, skuId);
        Object value = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));

        // 购物车信息
        CartInfo cartInfo;

        // 先判断购物车中是否已有该商品
        if (value == null) {
            // 没有就先添加
            cartInfo = new CartInfo();

            // 根据skuId查询商品sku信息,服务拆分后需要远程调用service-product模块
//            ProductSku productSku = productService.getBySkuId(skuId);

//            // 订阅service-product服务的实例列表
//            List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//            // 随机挑选一个实例,实现负载均衡
//            ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));
//            // 版本1：使用RestTemplate发送http请求
//            ResponseEntity<ProductSku> responseEntity = restTemplate.exchange(
//                    instance.getUri() + "/api/product/getBySkuId/{skuId}",  // 请求路径
//                    HttpMethod.GET,  // 请求方式
//                    null,  // 请求实体,可以为空
//                    new ParameterizedTypeReference<>() {},  // 返回值类型
//                    skuId  // 请求参数
//            );
//            // 解析响应结果
//            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
//                // 查询失败,直接结束
//                return;
//            }
//            // 获取响应体
//            ProductSku productSku = responseEntity.getBody();

            // 版本2：RestTemplate代码过于复杂,而且与原先的本地方法调用差异太大,远程调用应该和本地方法调用一样简单才对
            // 远程调用的三个关键点：服务名称、请求方式和路径、请求参数和返回值类型
            // OpenFeign就是利用SpringMVC的相关注解来声明上述信息,然后基于动态代理生成远程调用的代码
            // OpenFeign替我们完成了服务订阅、负载均衡、发送http请求等所有工作,看起来优雅多了
            ProductSku productSku = productFeignClient.getBySkuId(skuId);

            // 填充购物车相关信息
            cartInfo.setUserId(1L);
            cartInfo.setSkuId(skuId);
            cartInfo.setSkuNum(skuNum);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        } else {
            // 有就修改数量
            cartInfo = JSON.parseObject(value.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }

        // 将商品数据添加到购物车中
        log.info("购物车微服务 - 根据key和field添加商品：{},{}", cartKey, skuId);
        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

}