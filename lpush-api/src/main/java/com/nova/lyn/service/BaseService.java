package com.nova.lyn.service;

import com.nova.lyn.exception.ServiceException;
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

    protected void doStart(Listener listener) throws Throwable {
        listener.onSuccess();
    }
    protected void doStop(Listener listener) throws Throwable {
        listener.onSuccess();
    }

    protected void tryStart(Listener l, FunctionEx functionEx) {
        Listener listener = wrap(l);
        if (started.compareAndSet(false, true)) {
            try {

                init();
                functionEx.apply(listener);
                /**为服务添加监控*/
                ((FutureListener) listener).monitor(this);
            }catch (Throwable throwable){
                listener.onFailure(throwable);
                throw new ServiceException(throwable);
            }
        }else{
            if (throwIfStopped()) {
                listener.onFailure(new ServiceException("service already stopped"));
            }else {
                listener.onSuccess();
            }
        }
    }

    @Override
    public boolean syncStart() {
        return start().join();
    }

    @Override
    public boolean syncStop() {
        return stop().join();
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
        void apply(Listener listener) throws Throwable;
    }

    @Override
    public CompletableFuture<Boolean> start() {
        FutureListener listener = new FutureListener(started);
        start(listener);
        return listener;
    }

    @Override
    public CompletableFuture<Boolean> stop() {
        FutureListener listener = new FutureListener(started);
        stop(listener);
        return listener;
    }


    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void destroy() {

    }
}
