package com.nova.lyn.listener;

import com.nova.lyn.exception.ServiceException;
import com.nova.lyn.service.BaseService;
import com.nova.lyn.service.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/***
 * @ClassName: FutureListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午11:30
 * @version : V1.0
 */
public class FutureListener extends CompletableFuture<Boolean> implements Listener {

    private final Listener listener;
    private final AtomicBoolean started;

    public FutureListener(AtomicBoolean started) {
        this.started = started;
        this.listener = null;
    }

    public FutureListener(Listener listener, AtomicBoolean started) {
        this.started = started;
        this.listener = listener;
    }

    /**防止服务在某个地方卡住*/
    public void monitor(BaseService baseService) {
        runAsync(()->{
            try {
                this.get(baseService.timeoutMills(),TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                throw  new ServiceException(String.format("Service monitor %s timeout",baseService.getClass().getSimpleName()));
            }
        });
    }

    @Override
    public void onSuccess(Object... objects) {
        /**防止重复执行*/
        if (isDone()) return;
        complete(started.get());
        if (listener!=null) listener.onSuccess(objects);
    }

    @Override
    public void onFailure(Throwable throwable) {
        /**防止重复执行*/
        if (isDone()) return;
        completeExceptionally(throwable);
        if (listener!=null) listener.onFailure(throwable);
        throw throwable instanceof ServiceException?(ServiceException)throwable : new ServiceException(throwable);
    }
}
