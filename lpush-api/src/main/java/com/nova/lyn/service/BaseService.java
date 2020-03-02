package com.nova.lyn.service;

import com.nova.lyn.listener.FutureListener;
import com.nova.lyn.listener.Listener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/***
 * @ClassName: BaseService
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午11:19
 * @version : V1.0
 */
public abstract class BaseService implements Service {

    private AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void init(){

    }

    @Override
    public void start(Listener listener) {
        tryStart(listener,this::doStart);
    }

    @Override
    public void stop(Listener listener) {

    }

    protected void doStart(Listener listener) {
        listener.onSuccess();
    }

    protected void tryStart(Listener listener, FunctionEx functionEx) {
        Listener listener1 = wrap(listener);
        if (started.compareAndSet(false, true)) {
            init();
            functionEx.apply(listener1);
            /**为服务添加监控*/
            ((FutureListener) listener1).monitor(this);
        }
    }

    /**服务调用响应超时时间，默认10s*/
    public int timeoutMills() {
        return 1000 * 10;
    }

    /**重复调用系统启停是否抛出异常，默认为true*/
    private boolean throwIfStarted() {
        return true;
    }

    private boolean throwIfStopped() {
        return true;
    }

    /**防止listner被重复执行*/
    private FutureListener wrap(Listener listener) {
        if (listener==null) return new FutureListener(started);
        if (listener instanceof FutureListener) return (FutureListener) listener;
        return new FutureListener(listener, started);
    }

    protected interface FunctionEx {
        void apply(Listener listener);
    }

    @Override
    public CompletableFuture<Boolean> start() {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> stop() {
        return null;
    }


    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void destroy() {

    }
}
