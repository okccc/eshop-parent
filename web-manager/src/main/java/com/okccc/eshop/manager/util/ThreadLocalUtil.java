package com.okccc.eshop.manager.util;

/**
 * @Author: okccc
 * @Date: 2024/7/1 16:39:34
 * @Desc: ThreadLocal是jdk提供的线程工具类
 *
 * 如何解决线程并发问题
 * 1.同步机制：通过加锁保证同一时间只有一个线程访问该变量,该变量仍是多线程共享的,只不过锁的实现比较复杂且影响性能
 * 2.ThreadLocal：为每个线程提供一份独立变量,该变量只属于当前线程对其它线程是隔离的,可以实现同一个线程内的数据共享
 *
 * 前端请求后端接口会依次调用Controller/Service/Mapper,整个流程是在一个线程里执行的,此时就可以使用ThreadLocal
 *
 * 注意：泛型不能和静态一起使用,因为静态随着类的加载而加载,由类直接调用,而泛型是在创建对象时才确定具体类型,两者冲突
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
