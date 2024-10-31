package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.service.ProductSpecService;
import com.okccc.eshop.model.entity.product.ProductSpec;
import com.okccc.eshop.manager.mapper.ProductSpecMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 16:07:08
 * @Desc:
 */
@Slf4j
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public PageResult<ProductSpec> page(Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询
        log.info("商品规格管理 - 分页查询商品规格");
        Page<ProductSpec> page = productSpecMapper.selectPage();

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public void save(ProductSpec productSpec) {
        log.info("商品规格管理 - 添加商品规格：{}", productSpec);
        productSpecMapper.insert(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        log.info("商品规格管理 - 根据id修改商品规格：{}", productSpec);
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void removeById(Long id) {
        log.info("商品规格管理 - 根据id删除商品规格：{}", id);
        productSpecMapper.deleteById(id);
    }

    @Override
    public List<ProductSpec> list() {
        log.info("商品规格管理 - 查询所有商品规格");
        return productSpecMapper.selectPage();
    }

}
