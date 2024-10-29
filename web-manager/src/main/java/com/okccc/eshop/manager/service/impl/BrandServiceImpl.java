package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.service.BrandService;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.manager.mapper.BrandMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 10:38:40
 * @Desc:
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> page(Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询
        log.info("品牌管理 - 分页查询品牌");
        Page<Brand> page = brandMapper.selectPage();

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public void save(Brand brand) {
        log.info("品牌管理 - 添加品牌：{}", brand);
        brandMapper.insert(brand);
    }

    @Override
    public void updateById(Brand brand) {
        log.info("品牌管理 - 根据id修改品牌：{}", brand);
        brandMapper.updateById(brand);
    }

    @Override
    public void removeById(Long id) {
        log.info("品牌管理 - 根据id删除品牌：{}", id);
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> list() {
        log.info("品牌管理 - 查询所有品牌");
        return brandMapper.selectPage();
    }

}
