package com.okccc.eshop.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.okccc.eshop.manager.mapper.CategoryMapper;
import com.okccc.eshop.model.entity.product.CategoryExcel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/8 18:20:55
 * @Desc: Excel监听器 https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 */
// 监听器不能交给Spring管理,因为IOC容器中的Bean都是单例的,会存在并发问题
// 比如两个人同时上传不同的excel,但是容器中只有一个Listener对象,就会导致读取内容错乱
// 操作数据库的Spring组件可定义为成员变量,通过构造参数传入,每次上传excel都会创建新的Listener
@Slf4j
public class CategoryExcelListener implements ReadListener<CategoryExcel> {

    // 每隔100条存储数据库,然后清理list方便内存回收
    private static final int BATCH_COUNT = 100;

    // 缓存数据的列表
    private List<CategoryExcel> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    // 操作数据库的对象
    private final CategoryMapper categoryMapper;

    public CategoryExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 从第二行开始读取,每来一条数据就将其封装成CategoryExcel对象
     */
    @Override
    public void invoke(CategoryExcel categoryExcel, AnalysisContext analysisContext) {
        log.info("进来一条数据：{}", categoryExcel);

        // 添加读取的每行数据
        cachedDataList.add(categoryExcel);

        // 达到BATCH_COUNT就去存储一次数据库,防止几万条数据堆在内存导致OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            // 将缓存数据写入数据库
            saveData();

            // 清空缓存数据
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * excel所有数据解析完以后会执行该代码
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据,确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成");
    }

    /**
     * 保存数据
     */
    private void saveData() {
        log.info("导入excel - 批量写入数据：{}条", cachedDataList.size());
        categoryMapper.batchInsert(cachedDataList);
    }

}
