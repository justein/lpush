package com.nova.lyn.base;

import com.nova.lyn.listener.Listener;
import com.nova.lyn.server.NettyTCPServer;
import com.nova.lyn.server.Server;

/***
 * @ClassName: ServerBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午10:35
 * @version : V1.0
 */
public class ServerBoot extends BootService {

    /**创建TCP Server*/
    private final Server server = new NettyTCPServer(9800);
    @Override
    public void start() {
        server.init();
        server.start(new Listener() {
            @Override
            public void onSuccess(Object... objects) {
                System.out.println("服务节点注册到zk集群...");
                startNext();
            }

            @Override
            public void onFailure(Throwable throwable) {
                /**系统直接强制退出，卧槽暴力*/
                System.exit(-1);
            }
        });
    }

    @Override
    public void stop() {
        stopNext();
    }
}
