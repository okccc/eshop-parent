package com.okccc.eshop.common.util;

/**
 * @Author: okccc
 * @Date: 2024/8/6 14:33:49
 * @Desc: ThreadLocal是jdk提供的线程工具类
 */
public class ThreadLocalUtil {

    // 创建ThreadLocal对象
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    // 存储数据
    public static void setUser(Long userId) {
        threadLocal.set(userId);
    }

    // 获取数据
    public static Long getUser() {
        return threadLocal.get();
    }

    // 删除数据
    public static void removeUser() {
        threadLocal.remove();
    }

}
