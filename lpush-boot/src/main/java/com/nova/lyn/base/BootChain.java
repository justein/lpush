package com.nova.lyn.base;

import com.google.common.eventbus.EventBus;
import com.nova.lyn.event.ServerStartEvent;
import com.nova.lyn.event.ServerStopEvent;
import com.nova.lyn.factory.ServerEventListenerFactory;

/***
 * @ClassName: BootChain
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 下午10:08
 * @version : V1.0
 */
public class BootChain {

    /**定义一个BootService用于触发启动动作*/

    private final BootService boot = new BootService() {
        @Override
        public void start() {
            System.out.println("LPush Server begin to start");
            startNext();
        }

        @Override
        public void stop() {
            stopNext();
        }
    };

    /**因为要形成链式调用，所以需要有一个'游标'保存最后一个节点*/
    private BootService last = boot;

    public static BootChain chain() {
        return new BootChain();
    }

    public BootChain boot() {
        return this;
    }

    public void start() {
        boot.start();
    }

    public void stop() {
        boot.stop();
    }

    public BootChain setNext(BootService bootService) {
        //
        this.last = last.setNext(bootService);
        return this;
    }

    public void startNext() {

    }

    public void end() {
        setNext(new BootService() {
            @Override
            public void start() {
                System.out.println("LPush Server start success.");
                ServerEventListenerFactory.create().on(new ServerStartEvent());
            }

            @Override
            public void stop() {
                System.out.println("LPush Server stop success.");
                ServerEventListenerFactory.create().on(new ServerStopEvent());
            }

            @Override
            public String getName() {
                return "Last Boot";
            }
        });
    }
}
