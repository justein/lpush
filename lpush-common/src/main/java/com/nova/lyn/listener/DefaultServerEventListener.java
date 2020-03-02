package com.nova.lyn.listener;

import com.nova.lyn.event.ServerStartEvent;
import com.nova.lyn.event.ServerStopEvent;
import com.nova.lyn.factory.ServerEventListenerFactory;
import com.nova.lyn.utils.EventBusCenter;

/***
 * @ClassName: DefaultServerEventListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午5:13
 * @version : V1.0
 */
public class DefaultServerEventListener implements ServerEventListener, ServerEventListenerFactory {

    @Override
    public void on(ServerStartEvent serverStartEvent) {
        EventBusCenter.newInstance().post(serverStartEvent);
    }

    @Override
    public void on(ServerStopEvent serverStopEvent) {
        EventBusCenter.newInstance().post(serverStopEvent);
    }

    @Override
    public ServerEventListener get() {
        return this;
    }
}
