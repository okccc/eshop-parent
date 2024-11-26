package com.okccc.eshop.product.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okccc.eshop.model.dto.h5.ProductSkuDto;
import com.okccc.eshop.model.entity.product.Product;
import com.okccc.eshop.model.entity.product.ProductDetail;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.model.vo.h5.ProductItemVo;
import com.okccc.eshop.product.mapper.ProductDetailsMapper;
import com.okccc.eshop.product.mapper.ProductMapper;
import com.okccc.eshop.product.mapper.ProductSkuMapper;
import com.okccc.eshop.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:31:30
 * @Desc:
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public List<ProductSku> getProductSkuBySale() {
        log.info("商品sku管理 - 查询畅销商品");
        return productSkuMapper.selectListBySale();
    }

    @Override
    public PageInfo<ProductSku> page(Integer pageNum, Integer pageSize, ProductSkuDto productSkuDto) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询,带搜索条件
        log.info("商品sku管理 - 分页查询");
        List<ProductSku> list =  productSkuMapper.selectPage(productSkuDto);

        // 封装pageResult对象
        return new PageInfo<>(list);
    }

    @Override
    public ProductItemVo getById(Long skuId) {
        // 查询商品sku信息
        log.info("商品sku管理 - 根据id查询商品sku：{}", skuId);
        ProductSku productSku = productSkuMapper.selectById(skuId);

        // 查询商品信息
        Long productId = productSku.getProductId();
        log.info("商品管理 - 根据id查询商品：{}", productSku.getProductId());
        Product product = productMapper.selectById(productId);

        // 同一个商品下的sku信息列表
        log.info("商品sku管理 - 根据productId查询商品sku：{}", productId);
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(productId);

        // 建立sku规格与skuId对应关系
        Map<String, Object> skuSpecValueMap = new HashMap<>();
        for (ProductSku sku : productSkuList) {
            skuSpecValueMap.put(sku.getSkuSpec(), sku.getId());
        }

        // 查询商品详情信息
        log.info("商品详情管理 - 根据productId查询商品详情：{}", productId);
        ProductDetail productDetail = productDetailsMapper.selectById(productId);

        // 封装返回结果
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProduct(product);
        productItemVo.setProductSku(productSku);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetail.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);

        // 返回结果
        return productItemVo;
    }
}
