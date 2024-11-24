package com.okccc.eshop.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okccc.eshop.model.dto.h5.ProductSkuDto;
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
}
