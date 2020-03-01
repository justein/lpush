package com.nova.lyn.base;

/***
 * @ClassName: AdminBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 下午10:05
 * @version : V1.0
 */
public class AdminBoot extends BootService {
    @Override
    public void start() {
        System.out.println("AdminBoot Service start...");
        /**触发下一个组件启动*/
        startNext();
    }

    @Override
    public void stop() {
        System.out.println("AdminBoot Service stop...");
        /**触发下一个组件关闭*/
        stopNext();
    }
}
