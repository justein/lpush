package com.nova.lyn.monitor;

import java.util.concurrent.Executor;

/***
 * @ClassName: Monitor
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午4:38
 * @version : V1.0
 */
public interface Monitor {

    void monitor(String name, Thread thread);

    void monitor(String name, Executor executor);
}
