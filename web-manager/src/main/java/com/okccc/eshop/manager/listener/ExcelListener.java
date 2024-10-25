package com.okccc.eshop.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 10:56:00
 * @Desc:
 */
@Getter
public class ExcelListener<T> implements ReadListener<T> {

    // 封装数据的集合
    private final List<T> datas = new ArrayList<>();

    // 每来一条数据都会调用该方法,将读取内容封装成T对象
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        datas.add(t);
        // 对数据进行业务逻辑处理
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel解析完毕以后需要执行的代码
    }

}
