package com.okccc.eshop.product.mapper;

import com.okccc.eshop.model.dto.h5.ProductSkuDto;
import com.okccc.eshop.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/22 10:39:04
 * @Desc:
 */
@Mapper
public interface ProductSkuMapper {

    // 根据销量排序
    List<ProductSku> selectListBySale();

    // 分页查询,带搜索条件
    List<ProductSku> selectPage(ProductSkuDto productSkuDto);

    // 根据id查询商品sku
    ProductSku selectById(Long id);

    // 根据productId查询商品sku
    List<ProductSku> selectByProductId(Long productId);

    // 根据id更新商品销量和库存
    void updateById(@Param("id") Long id, @Param("num") Integer num);
}
