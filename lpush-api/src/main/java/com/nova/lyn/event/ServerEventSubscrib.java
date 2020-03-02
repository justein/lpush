package com.nova.lyn.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/***
 * @ClassName: ServerEventSubscrib
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/2 上午10:09
 * @version : V1.0
 */
public class ServerEventSubscrib {

    @Subscribe
    @AllowConcurrentEvents
    public void serverStartSub(ServerStartEvent serverStartEvent) {

        System.out.println("这是通过Listener发送过来的事件响应: "+serverStartEvent.getClass().getSimpleName());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void serverStopSub(ServerStopEvent serverStopEvent) {

        System.out.println("这是通过Listener发送过来的事件响应: "+serverStopEvent.getClass().getSimpleName());
    }
}
