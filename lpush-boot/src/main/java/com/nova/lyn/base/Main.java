package com.nova.lyn.base;

/***
 * @ClassName: Main
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 下午6:14
 * @version : V1.0
 */
public class Main {

    public static void main(String[] args) {

        ServerLauncher serverLauncher = new ServerLauncher();
        serverLauncher.init();

        /**服务退出时执行资源回收等操作*/

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("JVM进程退出，执行资源回收等操作")));
    }
}
