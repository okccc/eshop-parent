package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.model.dto.product.ProductDto;
import com.okccc.eshop.model.entity.product.Product;
import com.okccc.eshop.model.entity.product.ProductDetail;
import com.okccc.eshop.model.entity.product.ProductSku;
import com.okccc.eshop.manager.mapper.ProductDetailsMapper;
import com.okccc.eshop.manager.mapper.ProductMapper;
import com.okccc.eshop.manager.mapper.ProductSkuMapper;
import com.okccc.eshop.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

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

    @Override
    public Product getById(Long id) {
        // 根据id查询商品基本信息
        log.info("商品管理 - 根据id查询商品：{}", id);
        Product product = productMapper.selectById(id);

        // 根据id查询商品sku信息
        log.info("商品sku管理 - 根据parentId查询商品sku：{}", id);
        List<ProductSku> productSkuList = productSkuMapper.selectListByProductId(id);
        product.setProductSkuList(productSkuList);

        // 根据id查询商品详情信息
        log.info("商品详情管理 - 根据parentId查询商品详情：{}", id);
        ProductDetail productDetail = productDetailsMapper.selectByProductId(id);
        product.setDetailsImageUrls(productDetail.getImageUrls());

        // 返回结果
        return product;
    }

    @Override
    public void save(Product product) {
        // 保存商品基本信息
        product.setStatus(0);
        product.setAuditStatus(0);
        log.info("商品管理 - 添加商品：{}", product);
        productMapper.insert(product);

        // 保存商品sku信息
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0; i < productSkuList.size(); i++) {
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);
            productSku.setStatus(0);
            log.info("商品sku管理 - 添加商品sku：{}", productSku);
            productSkuMapper.insert(productSku);
        }

        // 保存商品详情信息
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(product.getId());
        productDetail.setImageUrls(product.getDetailsImageUrls());
        log.info("商品详情管理 - 添加商品详情：{}", productDetail);
        productDetailsMapper.insert(productDetail);
    }

    @Override
    public void updateById(Product product) {
        // 修改商品基本信息
        log.info("商品管理 - 根据id修改商品：{}", product);
        productMapper.updateById(product);

        // 修改商品sku信息
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (ProductSku productSku : productSkuList) {
            log.info("商品sku管理 - 根据id修改商品sku：{}", productSku);
            productSkuMapper.updateById(productSku);
        }

        // 修改商品详情信息
        log.info("商品详情管理 - 根据id查询商品详情：{}", product.getId());
        ProductDetail productDetail = productDetailsMapper.selectByProductId(product.getId());
        productDetail.setImageUrls(product.getDetailsImageUrls());
        log.info("商品详情管理 - 根据id修改商品详情：{}", productDetail);
        productDetailsMapper.updateById(productDetail);
    }

    @Override
    public void removeById(Long id) {
        // 删除商品基本信息
        log.info("商品管理 - 根据id删除商品：{}", id);
        productMapper.deleteById(id);

        // 删除商品sku信息
        log.info("商品sku管理 - 根据productId删除商品sku：{}", id);
        productSkuMapper.deleteByProductId(id);

        // 删除商品详情信息
        log.info("商品详情管理 - 根据productId删除商品详情：{}", id);
        productDetailsMapper.deleteByProductId(id);
    }

}
