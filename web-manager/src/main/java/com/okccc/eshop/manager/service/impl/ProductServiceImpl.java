package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.mapper.ProductMapper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.service.ProductService;
import com.okccc.eshop.model.dto.product.ProductDto;
import com.okccc.eshop.model.entity.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:42:02
 * @Desc:
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> page(Integer pageNum, Integer pageSize, ProductDto productDto) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询,带搜索条件
        log.info("商品管理 - 分页查询商品");
        Page<Product> page = productMapper.selectPage(productDto);

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

}
