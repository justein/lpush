package com.nova.lyn.factory;

import com.nova.lyn.listener.ServerEventListener;
import com.nova.lyn.spi.SPILoader;

/***
 * @ClassName: ServerEventListenerFactory
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午4:35
 * @version : V1.0
 */
public interface ServerEventListenerFactory extends Factory<ServerEventListener> {
    static ServerEventListener create() {return SPILoader.load(ServerEventListenerFactory.class).get();
    }
}
