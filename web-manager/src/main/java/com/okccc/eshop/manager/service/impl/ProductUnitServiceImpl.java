package com.okccc.eshop.manager.service.impl;

import com.okccc.eshop.manager.service.ProductUnitService;
import com.okccc.eshop.model.entity.product.ProductUnit;
import com.okccc.eshop.manager.mapper.ProductUnitMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 17:04:45
 * @Desc:
 */
@Slf4j
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> list() {
        log.info("商品单元管理 - 查询所有商品单元");
        return productUnitMapper.selectList();
    }
}
