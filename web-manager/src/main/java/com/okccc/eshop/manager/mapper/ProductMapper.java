package com.okccc.eshop.manager.mapper;

import com.github.pagehelper.Page;
import com.okccc.eshop.model.dto.product.ProductDto;
import com.okccc.eshop.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:42:30
 * @Desc:
 */
@Mapper
public interface ProductMapper {

    // 分页查询商品,带搜索条件
    Page<Product> selectPage(ProductDto productDto);
}
