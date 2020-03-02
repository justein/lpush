package com.nova.lyn.utils;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/***
 * @ClassName: EventBusCenter
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午5:27
 * @version : V1.0
 */
public class EventBusCenter {

    private static class EventBusHolder {

        private static Executor executor = Executors.newFixedThreadPool(10);

        private static EventBus eventBus = new EventBus("LPush-EventBus");

        private static AsyncEventBus asyncEventBus = new AsyncEventBus("LPush-AsynEventBus",executor);


    }

    private EventBusCenter(){}

    public static EventBus newInstance() {
        return EventBusHolder.eventBus;
    }

    public static AsyncEventBus newAsynInstance() {
        return EventBusHolder.asyncEventBus;
    }
}
