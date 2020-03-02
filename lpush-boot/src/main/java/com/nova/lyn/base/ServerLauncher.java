package com.nova.lyn.base;

/***
 * @ClassName: ServerLauncher
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 下午6:17
 * @version : V1.0
 */
/**服务启动入口类*/
public class ServerLauncher {

    public void init() {

        BootChain chain = BootChain.chain();
        chain.boot()
                .setNext(new ServerBoot())
                .setNext(new AdminBoot())
                .setNext(new MonitorBoot())
                .setNext(new HttpProxyBoot())
                .end();

        chain.start();

        /**模拟提供服务*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chain.stop();
    }
}
