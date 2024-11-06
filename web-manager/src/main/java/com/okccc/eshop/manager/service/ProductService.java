package com.okccc.eshop.manager.service;

import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.dto.product.ProductDto;
import com.okccc.eshop.model.entity.product.Product;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:41:53
 * @Desc:
 */
public interface ProductService {

    // 分页查询商品,带搜索条件
    PageResult<Product> page(Integer pageNum, Integer pageSize, ProductDto productDto);

    // 添加商品
    void save(Product product);

    // 修改商品
    void updateById(Product product);

    // 根据id删除商品
    void removeById(Long id);
}
