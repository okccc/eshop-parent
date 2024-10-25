package com.okccc.eshop.manager.listener;

import com.alibaba.excel.EasyExcel;
import com.okccc.eshop.model.entity.product.CategoryExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 11:21:06
 * @Desc:
 */
public class ExcelListenerDemo {

    /**
     * 往excel写数据
     */
    public static void writeToExcel() {
        // 模拟数据
        List<CategoryExcel> list = new ArrayList<>();
        list.add(new CategoryExcel(1001L,"数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcel( 1002L, "华为手机" , "",1L, 1, 1)) ;
        list.add(new CategoryExcel( 1003L, "苹果电脑" , "",1L, 1, 1)) ;

        // 调用write方法
        EasyExcel
                .write("/Users/okc/Downloads/01.xlsx", CategoryExcel.class)
                .sheet("分类数据")
                .doWrite(list);
    }

    /**
     * 从excel读数据
     */
    public static void readFromExcel() {
        // 调用read方法
        ExcelListener<CategoryExcel> excelListener = new ExcelListener<>();
        EasyExcel
                .read("/Users/okc/Downloads/01.xlsx", CategoryExcel.class, excelListener)
                .sheet()
                .doRead();

        // 获取数据
        List<CategoryExcel> data = excelListener.getDatas();
        System.out.println(data);
    }

    public static void main(String[] args) {
//        writeToExcel();
        readFromExcel();
    }
}
