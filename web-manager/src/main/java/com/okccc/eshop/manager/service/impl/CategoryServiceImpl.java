package com.okccc.eshop.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.mapper.CategoryMapper;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.service.CategoryService;
import com.okccc.eshop.model.entity.product.Category;
import com.okccc.eshop.model.entity.product.CategoryExcel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:30:21
 * @Desc:
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getByParentId(Long parentId) {
        // 根据父分类查询它下面的所有子分类
        log.info("分类管理 - 根据parentId查询子分类：{}", parentId);
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);

        for (Category category : categoryList) {
            // 判断当前分类是否有子分类
            log.info("分类管理 - 根据id查询子分类数量：{}", category.getId());
            int count = categoryMapper.countByParentId(category.getId());
            category.setHasChildren(count > 0);
        }

        // 返回结果
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 1.设置响应头相关信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 文件名要防止中文乱码
            String fileName = URLEncoder.encode("分类数据", StandardCharsets.UTF_8);
            response.setHeader("Content-disposition","attachment;filename=" + fileName + ".xlsx");

            // 2.查询所有分类
            log.info("分类管理 - 查询所有分类,准备导出数据");
            List<Category> categoryList = categoryMapper.selectList();
            // 封装CategoryExcel的集合
            List<CategoryExcel> categoryExcelList = new ArrayList<>();
            for(Category category : categoryList) {
                // 将Category转换成CategoryExcel
                CategoryExcel categoryExcel = new CategoryExcel();
                BeanUtils.copyProperties(category, categoryExcel);
                categoryExcelList.add(categoryExcel);
            }

            // 3.使用EasyExcel完成写操作
            EasyExcel
                    .write(response.getOutputStream(), CategoryExcel.class)
                    .sheet("分类数据")
                    .doWrite(categoryExcelList);
        } catch (Exception e) {
            throw new MyRuntimeException(ResultCodeEnum.DATA_ERROR);
        }
    }

}
