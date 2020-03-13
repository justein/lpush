package com.nova.lyn.service;

import com.nova.lyn.listener.Listener;

import java.util.concurrent.CompletableFuture;

/***
 * @ClassName: Service
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午9:56
 * @version : V1.0
 */
public interface Service {

    /**常规启停*/
    void start(Listener listener);

    void stop(Listener listener);

    /**异步启停*/
    CompletableFuture<Boolean> start();

    CompletableFuture<Boolean> stop();
    /**同步启动*/
    boolean syncStart();
    /**同步关闭*/
    boolean syncStop();
    /**服务初始化*/
    void init();
    /**服务运行状态*/
    boolean isRunning();
    /**服务销毁*/
    void destroy();
}
