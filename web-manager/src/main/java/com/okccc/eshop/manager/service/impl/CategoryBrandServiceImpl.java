package com.okccc.eshop.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.okccc.eshop.manager.result.PageResult;
import com.okccc.eshop.manager.service.CategoryBrandService;
import com.okccc.eshop.model.dto.product.CategoryBrandDto;
import com.okccc.eshop.model.entity.product.Brand;
import com.okccc.eshop.model.entity.product.CategoryBrand;
import com.okccc.eshop.manager.mapper.CategoryBrandMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/9 14:21:10
 * @Desc:
 */
@Slf4j
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageResult<CategoryBrand> page(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 分页查询,带搜索条件
        log.info("分类品牌管理 - 分页查询分类品牌");
        Page<CategoryBrand> page = categoryBrandMapper.selectPage(categoryBrandDto);

        // 封装pageResult对象
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        log.info("分类品牌管理 - 添加分类品牌：{}", categoryBrand);
        categoryBrandMapper.insert(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        log.info("分类品牌管理 - 根据id修改分类品牌：{}", categoryBrand);
        categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public void removeById(Long id) {
        log.info("分类品牌管理 - 根据id删除分类品牌：{}", id);
        categoryBrandMapper.deleteById(id);
    }

    @Override
    public List<Brand> listByCategoryId(Long categoryId) {
        log.info("分类品牌管理 - 根据categoryId查询三级分类对应的品牌：{}", categoryId);
        return categoryBrandMapper.selectBrandListByCategoryId(categoryId);
    }

}
