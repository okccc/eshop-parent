package com.okccc.eshop.product.service.impl;

import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.product.mapper.ProductSkuMapper;
import com.okccc.eshop.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:31:30
 * @Desc:
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> getProductSkuBySale() {
        log.info("商品sku管理 - 查询畅销商品");
        return productSkuMapper.selectListBySale();
    }
}
