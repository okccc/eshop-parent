package com.okccc.eshop.product.service.impl;

import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.product.mapper.BrandMapper;
import com.okccc.eshop.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/21 18:53:55
 * @Desc:
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> list() {
        log.info("品牌管理 - 查询所有品牌");
        return brandMapper.selectList();
    }
}
