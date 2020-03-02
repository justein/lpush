package com.nova.lyn.listener;

import com.nova.lyn.event.ServerStartEvent;
import com.nova.lyn.event.ServerStopEvent;

/***
 * @ClassName: ServerEventListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午4:44
 * @version : V1.0
 */
public interface ServerEventListener {

    /**通过guava eventbus 进行事件派发*/
    default void on(ServerStartEvent serverStartEvent){}

    default void on(ServerStopEvent serverStopEvent){}
}
