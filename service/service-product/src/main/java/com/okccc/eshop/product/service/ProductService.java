package com.okccc.eshop.product.service;

import com.github.pagehelper.PageInfo;
import com.okccc.eshop.model.dto.h5.ProductSkuDto;
import com.okccc.eshop.model.entity.product.ProductSku;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/16 11:31:21
 * @Desc:
 */
public interface ProductService {

    // 查询畅销商品列表
    List<ProductSku> getProductSkuBySale();

    // 分页查询,带搜索条件
    PageInfo<ProductSku> page(Integer pageNum, Integer pageSize, ProductSkuDto productSkuDto);
}
